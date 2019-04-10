package com.elena.passport_checking_1.model;

import com.elena.passport_checking_1.metrics.Metrics;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="second_name")
    private String secondName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="sex")
    private String sex;

    @Column(name="place_of_birth")
    private String birthplace;

    @Column(name="place_of_issue")
    private String deliveryPlace;

    @Column(name="date_of_issue")
    private Date deliveryDay;

    @Column(name="passport_id")
    private Integer number;

    @Column(name="passport_series")
    private Integer series;

    public Passport() {
        super();
    }

    public Passport(String firstName, String secondName, String lastName, Date birthday,
                    String sex, String birthplace, String deliveryPlace, Date deliveryDay, Integer number, Integer series) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.birthplace = birthplace;
        this.deliveryPlace = deliveryPlace;
        this.deliveryDay = deliveryDay;
        this.number = number;
        this.series = series;
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

    public Integer getNumber() {
        return number;
    }

    public Integer getSeries() {
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

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setSeries(Integer series) {
        this.series = series;
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
