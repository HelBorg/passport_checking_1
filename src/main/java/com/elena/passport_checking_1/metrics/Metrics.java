package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

public class Metrics {
//    private WordMetrics firstNameMetrics;
//    private WordMetrics secondNameMetrics;
//    private WordMetrics lastNameMetrics;
//    private int ageOfGetting;
//    private int lenSeries;
//    private int lenNumber;
//
    private List<Metric> metrics = new ArrayList<Metric>();

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void updateMetrics(Passport p) {
        metrics.add(new AgeOfGetting(p));

//        updateFNM(p.getFirstName());
//        updateSNM(p.getSecondName());
//        updateLNM(p.getLastName());
//        updateAge(p.getBirthday(), p.getDeliveryDay());
    }
//
//    public void updateFNM(String firstName) {
//        firstNameMetrics.updateWordMetrics(firstName);
//    }
//
//    public void updateSNM(String secondName) {
//        secondNameMetrics.updateWordMetrics(secondName);
//    }
//
//    public void updateLNM(String lastName) {
//        lastNameMetrics.updateWordMetrics(lastName);
//    }
//
//    public void updateAge(Date deliveryDay, Date birthday) {
//        Calendar delivery = Calendar.getInstance();
//        delivery.setTime(deliveryDay);
//        Calendar birth = Calendar.getInstance();
//        birth.setTime(birthday);
//
//        this.ageOfGetting = delivery.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
//    }
//
//    public void updateLenSeries(int series) {
//        this.lenSeries = (int) floor(log10(series)) + 1;
//    }
//
//    public void updateLenNumber(int number) {
//        this.lenNumber = (int) floor(log10(number)) + 1;
//    }
//
//    public WordMetrics getFirstNameMetrics() {
//        return firstNameMetrics;
//    }
//
//    public WordMetrics getSecondNameMetrics() {
//        return secondNameMetrics;
//    }
//
//    public WordMetrics getLastNameMetrics() {
//        return lastNameMetrics;
//    }
//
//    public int getAgeOfGetting() {
//        return ageOfGetting;
//    }
//
//    public int getLenSeries() {
//        return lenSeries;
//    }
//
//    public int getLenNumber() {
//        return lenNumber;
//    }
}

