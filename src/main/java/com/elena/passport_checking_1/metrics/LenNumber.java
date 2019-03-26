package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

public class LenNumber implements Metric<Integer> {
    private Integer len;
    protected String field;

    public LenNumber(Passport p, String field) {
        this.field = field;
        this.calc(p);
    }

    @Override
    public void calc(Passport p) {
        Integer str = (int) p.getField(field);
        this.len = (int) floor(log10(str)) + 1;
    }

    @Override
    public Integer getValue() {
        return len;
    }
}
