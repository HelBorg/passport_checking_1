package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public abstract class Vowels extends FieldMetrics<String> {
    private Integer vowels;

    public Vowels(Passport p) {
        this.calc(p);
    }

    public Vowels(Integer vowels) {
        this.vowels = vowels;
    }

    @Override
    public void calc(Passport p) {
        String str = getFieldValue(p);
        this.vowels = str.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
    }

    @Override
    public Integer getValue() {
        return vowels;
    }
}
