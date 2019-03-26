package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public class LenString implements Metric<Integer> {
    private Integer len;
    public String field;

    public LenString(Passport p, String field) {
        this.field = field;
        this.calc(p);
    }

    @Override
    public void calc(Passport p) {
        String str = p.getField(this.field);
        this.len = str.length();
    }

    @Override
    public Integer getValue() {
        return len;
    }
}
