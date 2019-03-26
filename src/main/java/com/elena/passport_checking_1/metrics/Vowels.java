package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public class Vowels implements Metric<Integer> {
    private Integer vowels;
    public String field;

    public Vowels(Passport p, String str) {
        this.field = str;
        this.calc(p);
    }

    @Override
    public void calc(Passport p) {
        String str = p.getField(this.field);
        this.vowels = str.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
    }

    @Override
    public Integer getValue() {
        return vowels;
    }
}
