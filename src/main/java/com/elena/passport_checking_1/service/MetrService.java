package com.elena.passport_checking_1;

import com.elena.passport_checking_1.metrics.Metric;
import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Service {
    private TransportClient client;
    private Scanner scanner;

    public Service(TransportClient client) {
        this.client = client;
    }

    public IndexResponse add(TransportClient client, String index, String type, Metrics metrics) throws IOException, ExecutionException, InterruptedException {
        System.out.println("By what id put new data?\n");
        String id = this.scanner.nextLine();

        if (!this.exist(client, index, type, id)) {
            this.delete(client, index, type, id);
        }

        for (Metric<Integer> metric : metrics.getMetrics()) {
//            XContentBuilder builder = XContent
        }
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
//                .field("firstname", passport.getFirstName())
                .endObject();

        IndexResponse response = client.prepareIndex(index, type, id)
                .setSource(builder).get();
        return response;
    }

    public IndexResponse update(TransportClient client, String index, String type, Metrics metrics) throws InterruptedException, ExecutionException, IOException {
        return this.add(client, index, type, metrics);
    }

    public GetResponse get(TransportClient client, String index, String type) throws ExecutionException, InterruptedException {
        System.out.println("Write down an id\n");
        String id = scanner.nextLine();
        GetResponse response = client.get(new GetRequest(index, type, id)).get();
        return response;
    }

    public boolean exist(TransportClient client, String index, String type, String id) throws ExecutionException, InterruptedException {
        GetResponse response = client.get(new GetRequest(index, type, id)).get();
        System.out.println(response);
        System.out.println(response.getField("found"));
        return true;
    }

    public DeleteResponse delete(TransportClient client, String index, String type, String id) throws ExecutionException, InterruptedException {
        if (id.equals("-1")) {
            System.out.println(" Write down id\n");
            id = scanner.nextLine();
        }
        DeleteResponse response = client.delete(new DeleteRequest(index,type,id)).get();
        System.out.println(response);
        return response;
    }
}
