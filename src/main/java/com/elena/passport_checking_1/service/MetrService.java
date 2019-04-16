package com.elena.passport_checking_1.service;

import com.elena.passport_checking_1.metrics.Metric;
import com.elena.passport_checking_1.metrics.Metrics;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.sql.Types.NULL;

public class MetrService {
    private TransportClient client;
    private Integer maxNumber;

    public MetrService(TransportClient client) throws ExecutionException, InterruptedException {
        this.client = client;
        this.maxNumber = (int) client.get(new GetRequest("passport_checking", "metrics", "0"))
                .get().getSource().get("0");
    }

    public void updateNumOfRec() throws IOException {
        client.prepareIndex("passport_checking", "metrics", "0").setSource(
                XContentFactory.jsonBuilder().startObject().field("0", maxNumber).endObject());
    }

    public void add(Metrics metrics, int passport_id)
            throws IOException, ExecutionException, InterruptedException {

        if (passport_id > maxNumber) {
            maxNumber = passport_id;
        }

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        for (Metric<Integer> metric : metrics.getMetrics()) {
            builder.field(Integer.toString(maxNumber), metric.getValue());
            this.maxNumber = this.maxNumber + 1;
        }
        builder.endObject();
        IndexResponse response = client.prepareIndex("passport_checking", "metrics",
                Integer.toString(passport_id)).setSource(builder).get();
        this.updateNumOfRec();
    }

    public Map<String, Object> get(int passport_id) throws ExecutionException, InterruptedException {
        GetResponse response = client.get(new GetRequest("passport_checking", "metrics",
                Integer.toString(passport_id))).get();
        return response.getSource();
    }

    public List<Map<String, Object>> getAll() throws ExecutionException, InterruptedException {
        List<Map<String, Object>> listMetrics = new ArrayList<>();
        MultiGetRequest request = new MultiGetRequest();
        for (int i = 1; i < maxNumber; i++) {
            request.add("passport_checking", "metrics", Integer.toString(i));
        }
        MultiGetResponse response = client.multiGet(request).get();
        for (int i = 0; i < response.getResponses().length; i++) {
            listMetrics.add(response.getResponses()[i].getResponse().getSource());
        }
        return listMetrics;
    }

    public boolean exist(int id) throws ExecutionException, InterruptedException {
        GetResponse response = client.get(new GetRequest("passport_checking", "metrics",
                Integer.toString(id))).get();
        System.out.println(response);
        System.out.println(response.getField("found"));
        return true;
    }

    public DeleteResponse delete(int id) throws ExecutionException, InterruptedException, IOException {
        DeleteResponse response = client.delete(new DeleteRequest("passport_checking", "metrics",
                Integer.toString(id))).get();
        this.updateNumOfRec();
        return response;
    }

    public List<Double> getMax(Long metric_id) {
        SearchResponse sr = client
                .prepareSearch("passport_checking")
                .setTypes("metrics")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.max("max").field(Long.toString(metric_id)))
                .execute().actionGet();
        List<Double> list  = new ArrayList<>();
        for (Aggregation maxAggs : sr.getAggregations()) {
            Max max = (Max) maxAggs;
            list.add(max.getValue());
        }
        return list;
    }

    public List<Double> getMin(Long metric_id) {
        SearchResponse sr = client
                .prepareSearch("passport_checking")
                .setTypes("metrics")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.min("min").field(Long.toString(metric_id)))
                .execute().actionGet();
        List<Double> list  = new ArrayList<>();
        for (Aggregation minAggs : sr.getAggregations()) {
            Min min = (Min) minAggs;
            list.add(min.getValue());
        }
        return list;
    }

    public Double getAverage(Long metric_id) {
        SearchResponse sr = client
                .prepareSearch("passport_checking")
                .setTypes("metrics")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.avg("avg").field(Long.toString(metric_id)))
                .execute().actionGet();
        Avg avgA = (Avg) sr.getAggregations();
        return avgA.getValue();
    }

    public Double getVariance(Long metric_id) {
        SearchResponse sr = client
                .prepareSearch("passport_checking")
                .setTypes("metrics")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.extendedStats("variance").field(Long.toString(metric_id)))
                .execute().actionGet();
        Avg avgA = (Avg) sr.getAggregations();
        return avgA.getValue();
    }
}
