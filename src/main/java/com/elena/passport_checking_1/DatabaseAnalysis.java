package com.elena.passport_checking_1;

import com.elena.passport_checking_1.controller.MetrController;
import com.elena.passport_checking_1.controller.PassController;
import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DatabaseAnalysis {
    public static void calcMetricsDB(ConfigurableApplicationContext context) throws InterruptedException, ExecutionException, IOException {
        PassController pController = (PassController) context.getBeanFactory().getBean("passController");
        MetrController mController = (MetrController) context.getBeanFactory().getBean("metrController");

        for (int i = 1; i < 1001; i = i + 1) {
            System.out.println(i);
            Passport p = pController.getPassportById(new Long(i)).get(0);
            Metrics metrics = mController.calcMetrics(p);
            mController.saveMetrics(metrics, i);
        }
        mController.exit();
    }

    public static void analyseMetrics(ConfigurableApplicationContext context) {
        MetrController mController = (MetrController) context.getBeanFactory().getBean("metrController");

    }

}
