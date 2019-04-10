package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public abstract class Consonants extends FieldMetrics<String> {
    private Integer consonants;

    public Consonants(Passport p) {
        this.calc(p);
    }

    public Consonants(Integer consonants) {
        this.consonants = consonants;
    }

    @Override
    public void calc(Passport p) {
        String str = getFieldValue(p);
        this.consonants = str.length() - str.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
    }

    @Override
    public Integer getValue() {
        return consonants;
    }
}
