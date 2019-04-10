package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

public abstract class LenNumber extends FieldMetrics<Integer> {
    private Integer len;

    public LenNumber(Passport p) {
        this.calc(p);
    }

    public LenNumber(Integer len) {
        this.len = len;
    }

    @Override
    public void calc(Passport p) {
        Integer str = getFieldValue(p);
        this.len = new Integer((int)(floor(log10(str)) + 1));
    }

    @Override
    public Integer getValue() {
        return len;
    }
}
