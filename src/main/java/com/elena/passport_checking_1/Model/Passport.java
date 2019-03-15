package com.elena.passport_checking_1.model;

import java.util.Date;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

public class Passport {
    private String firstName;
    private String secondName;
    private String lastName;

    private String sex;

    private int number;
    private int lenNumber;

    private int series;
    private int lenSeries;

    private Date birthday;

    private String birthplace;

    private Date deliveryDay;

    private String deliveryPlace;

    public Passport() {
        super();
    }

    public Passport(String firstName, String secondName, String lastName,
                    String sex, int number, int lenNumber, int series,
                    int lenSeries, Date birthday, String birthplace,
                    Date deliveryDay, String deliveryPlace) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.sex = sex;
        this.number = number;
        this.lenNumber = lenNumber;
        this.series = series;
        this.lenSeries = lenSeries;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.deliveryDay = deliveryDay;
        this.deliveryPlace = deliveryPlace;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public int getNumber() {
        return number;
    }

    public int getSeries() {
        return series;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public Date getDeliveryDay() {
        return deliveryDay;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setNumber(int number) {
        this.number = number;
        updateLenNumber();
    }

    public void updateLenNumber() {
        this.lenNumber = (int) floor(log10(this.number)) + 1;
    }

    public void setSeries(int series) {
        this.series = series;
        updateLenSeries();
    }

    public void updateLenSeries() {
        this.lenSeries = (int) floor(log10(this.series)) + 1;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setDeliveryDay(Date deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }
}
