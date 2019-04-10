package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

import java.util.Calendar;

public class AgeOfGetting implements Metric<Integer> {
    private Integer ageOfGetting;

    public AgeOfGetting(Passport p) {
        this.calc(p);
    }

    public AgeOfGetting(Integer ageOfGetting) {
        this.ageOfGetting = ageOfGetting;
    }

    @Override
    public void calc(Passport p) {
        Calendar delivery = Calendar.getInstance();
        delivery.setTime(p.getDeliveryDay());
        Calendar birth = Calendar.getInstance();
        birth.setTime(p.getBirthday());

        ageOfGetting = delivery.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
    }

    @Override
    public Integer getValue() {
            return ageOfGetting;
        }

}
