package com.elena.passport_checking_1;

import com.elena.passport_checking_1.controller.PassController;
import com.elena.passport_checking_1.controller.MetrController;
import com.elena.passport_checking_1.metrics.Metric;
import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.hibernate5.SpringSessionContext;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@SpringBootApplication
public class PassportChecking1Application {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        ConfigurableApplicationContext context = SpringApplication.run(PassportChecking1Application.class, args);
//        DatabaseAnalysis.calcMetricsDB(context);
        MetrController mController = (MetrController) context.getBeanFactory().getBean("metrController");
        List<Metrics> list = new ArrayList<>();
        list.add(mController.getMetrics("1"));
        mController.writeIntoExcel(list, 1L);
        try {
            System.out.println("ok");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
