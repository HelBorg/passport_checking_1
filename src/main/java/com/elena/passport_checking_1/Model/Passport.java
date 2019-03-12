package com.elena.passport_checking_1.Model;

import java.util.Date;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

public class Passport {
    private FullName fullName;

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

    public Passport(FullName fullName, String sex, int number, int series,
                    Date birthday, String birthplace, Date deliveryDay,
                    String deliveryPlace) {
        this.fullName = fullName;
        this.sex = sex;
        this.number = number;
        updateLenNumber();
        this.series = series;
        updateLenSeries();
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.deliveryDay = deliveryDay;
        this.deliveryPlace = deliveryPlace;
    }

    public FullName getFullName() {
        return fullName;
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

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
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
