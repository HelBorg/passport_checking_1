package com.elena.passport_checking_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PassportChecking1Application {

    public static void main(String[] args) {
        SpringApplication.run(PassportChecking1Application.class, args);
        try{
            ElasService.Service();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
