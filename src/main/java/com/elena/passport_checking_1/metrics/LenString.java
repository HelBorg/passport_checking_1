package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public abstract class LenString extends FieldMetrics<String> {
    private Integer len;

    public LenString(Passport p) {
       this.calc(p);
    }

    public LenString(Integer len) {
        this.len = len;
    }

    @Override
    public void calc(Passport p) {
        String str = getFieldValue(p);
        this.len = str.length();
    }

    @Override
    public Integer getValue() {
        return len;
    }


}
