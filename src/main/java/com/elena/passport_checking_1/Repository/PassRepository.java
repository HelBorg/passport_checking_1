package com.elena.passport_checking_1.repository;

import com.elena.passport_checking_1.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassRepos extends JpaRepository<Passport, String> {
}
