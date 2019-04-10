package com.elena.passport_checking_1.repository;

import com.elena.passport_checking_1.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Passport, Long> {
    List<Passport> getPassportById(Long id);

    @Override
    List<Passport> findAll();

    List<Passport> getPassportsById(Long id);
}
