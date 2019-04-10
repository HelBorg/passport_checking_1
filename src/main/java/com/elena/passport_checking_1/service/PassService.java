package com.elena.passport_checking_1.service;

import com.elena.passport_checking_1.model.Passport;

import java.util.List;

public interface PassService {
    List<Passport> findAll();

    List<Passport> getById(Long id);
}
