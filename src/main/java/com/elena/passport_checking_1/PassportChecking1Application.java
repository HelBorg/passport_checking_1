package com.elena.passport_checking_1;

import com.elena.passport_checking_1.controller.PassController;
import com.elena.passport_checking_1.controller.MetrController;
import com.elena.passport_checking_1.metrics.Metric;
import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger log = LoggerFactory.getLogger(MetrController.class);

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        ConfigurableApplicationContext context = SpringApplication.run(PassportChecking1Application.class, args);
        MetrController mController = (MetrController) context.getBeanFactory().getBean("metrController");
        List<Double> max = mController.getMaxForMetric(0l);
        List<Double> min = mController.getMinForMetric(0L);
        System.out.println("Max => " + max);
        System.out.println("Max => " + min);
        try {
            log.info("ok");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
