package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public class Consonants implements Metric<Integer> {
    private Integer consonants;
    public String field;

    public Consonants(Passport p, String str) {
        this.field = str;
        this.calc(p);
    }

    @Override
    public void calc(Passport p) {
        String str = (String)p.getField(this.field);
        this.consonants = str.length() - str.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
    }

    @Override
    public Integer getValue() {
        return consonants;
    }
}
