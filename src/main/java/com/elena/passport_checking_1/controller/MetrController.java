package com.elena.passport_checking_1.controller;

import com.elena.passport_checking_1.metrics.Metric;
import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import com.elena.passport_checking_1.service.MetrService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api")
public class MetrController {
    private final Logger log = LoggerFactory.getLogger(MetrController.class);
    private MetrService service;
    private TransportClient client;

    public MetrController() throws UnknownHostException, ExecutionException, InterruptedException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        service = new MetrService(client);
    }

    public void saveMetrics(Metrics metrics, int passport_id) throws InterruptedException, ExecutionException, IOException {
        service.add(metrics, passport_id);
    }

    public void getMetricsByPassportId(int passport_id) throws ExecutionException, InterruptedException {
        service.get(passport_id);
    }

    public Metrics calcMetrics(Passport passport) {
        return new Metrics(passport);
    }

    public void exit() {
        client.close();
    }

    @GetMapping("/{metricsId}")
    public Metrics getMetrics(@PathVariable("metricsId") String metricsId) throws ExecutionException, InterruptedException {
        int id =  Integer.parseInt(metricsId);
        return new Metrics(service.get(id));
    }

    @PostMapping
    public ResponseEntity<Metrics> createMetrics(@RequestBody Metrics metrics, int id) throws InterruptedException, ExecutionException, IOException {
        service.add(metrics, id);
        return ResponseEntity.ok().body(metrics);
    }

    @GetMapping("/metrics")
    public Collection<Metrics> getMetrics() throws ExecutionException, InterruptedException {
        List<Metrics> listMetrics = new ArrayList<>();
        for(Map<String, Object> map: service.getAll()) {
            listMetrics.add(new Metrics(map));
        }
        log.info("response is gotten");
        return listMetrics;
    }

    @PutMapping
    public ResponseEntity<Metrics> updateCompany(@RequestBody Metrics metrics, int id) throws InterruptedException, ExecutionException, IOException {
        service.add(metrics, id);
        return ResponseEntity.ok().body(metrics);
    }

    @DeleteMapping("/{metricsId}")
    public ResponseEntity deleteMetrics(@PathVariable("metricsId") String metricsId) throws ExecutionException, InterruptedException, IOException {
        int id = Integer.parseInt(metricsId);
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public List<Double> getMaxForMetric(Long metric_id) {
        return service.getMax(metric_id);
    }

    public List<Double> getMinForMetric(Long metric_id) {
        return service.getMin(metric_id);
    }

    public Double getAverage(Long metric_id) {
        return service.getAverage(metric_id);
    }

    public void writeIntoExcel(List<Metrics> metricsList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Metrics");

        int rownum = 0;
        int cellNum = 0;
        Cell cell;
        Row row;

        // Data
        for (Metrics metrics : metricsList) {
            row = sheet.createRow(rownum);
            rownum++;
            for (Metric metric : metrics.getMetrics()) {
                cell = row.createCell(cellNum, CellType.STRING);
                cell.setCellValue((Integer)metric.getValue());
                cellNum++;
            }
            cellNum = 0;
        }
        File file = new File("C:/Users/mylet/IdeaProjects/passport_searching_1/files/metrics.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
        log.info("Data was successfully written into Excel");
    }
}
