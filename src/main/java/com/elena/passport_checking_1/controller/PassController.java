package com.elena.passport_checking_1.controller;

import com.elena.passport_checking_1.metrics.Metrics;
import com.elena.passport_checking_1.model.Passport;
import com.elena.passport_checking_1.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PassController {
    @Autowired
    PassService service;

    public PassController() {
        super();
    }

    public List<Passport> getPassportById(Long id) {
        return service.getById(id);
    }

    public List<Passport> getPassports() {
        return service.findAll();
    }
}
