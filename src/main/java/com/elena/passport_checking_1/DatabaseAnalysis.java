package com.elena.passport_checking_1;

import com.elena.passport_checking_1.controller.MetrController;
import com.elena.passport_checking_1.controller.PassController;
import com.elena.passport_checking_1.metrics.Metric;
import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DatabaseAnalysis {
    public static Long numMetr = 15L;

    public static void calcMetricsDB(ConfigurableApplicationContext context) throws InterruptedException, ExecutionException, IOException {
        PassController pController = (PassController) context.getBeanFactory().getBean("passController");
        MetrController mController = (MetrController) context.getBeanFactory().getBean("metrController");

        for (int i = 1; i < 1001; i = i + 1) {
            System.out.println(i);
            Passport p = pController.getPassportById((long) i).get(0);
            Metrics metrics = mController.calcMetrics(p);
            mController.saveMetrics(metrics, i);
        }
        mController.exit();
    }

    public static void calcMetrcsNewPass(ConfigurableApplicationContext context) {
        PassController pController = (PassController) context.getBeanFactory().getBean("passController");
        MetrController mController = (MetrController) context.getBeanFactory().getBean("metrController");

        System.out.println("mController.getAverage(1L)");
    }

    public static void writeMetricsIntoExcel(ConfigurableApplicationContext context) throws ExecutionException, InterruptedException, IOException {
        MetrController mController = (MetrController) context.getBeanFactory().getBean("metrController");
        List<Metrics> list = new ArrayList<>();
        for (int i = 1; i < 1001; i++) {
            Metrics metrics = mController.getMetrics(Integer.toString(i));
            list.add(metrics);
        }
        mController.writeIntoExcel(list);
    }

}
