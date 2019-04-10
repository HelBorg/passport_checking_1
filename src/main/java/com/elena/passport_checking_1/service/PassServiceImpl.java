package com.elena.passport_checking_1.service;

import com.elena.passport_checking_1.model.Passport;
import com.elena.passport_checking_1.repository.PassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassServiceImpl implements PassService {
    @Autowired
    private PassRepository repository;

    @Override
    public List<Passport> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Passport> getById(Long id) {
        List<Passport> passportById = repository.getPassportById(id);
        return passportById;
    }
}
