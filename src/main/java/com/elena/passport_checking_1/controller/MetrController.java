package com.elena.passport_checking_1.controller;

import com.elena.passport_checking_1.metrics.Metric;
import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import com.elena.passport_checking_1.service.MetrService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.sql.Types.NULL;

@RestController
@RequestMapping("/api/")
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
        Metrics metrics = new Metrics(service.get(1));
        return metrics;
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
        log.info("response is got");
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

    public Integer getMax(Long metric_id) {
        return NULL;
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void writeIntoExcel(List<Metrics> metricsList, Long id) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Metrics");

        int rownum = 0;
        int cellNum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        // Data
        for (Metrics metrics : metricsList) {
            row = sheet.createRow(rownum);
            rownum++;
            for (Metric metric : metrics.getMetrics()) {
                cell = row.createCell(cellNum, CellType.STRING);
                cellNum++;
                cell.setCellValue((Integer)metric.getValue());
            }
        }
        File file = new File("C:/demo/employee.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
