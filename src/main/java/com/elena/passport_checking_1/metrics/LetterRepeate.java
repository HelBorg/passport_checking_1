package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public class LetterRepeate implements Metric<Integer> {
    private Integer letterRepeat;
    public String field;

    protected LetterRepeate(Passport p, String field) {
        String str = p.getField(field);
        this.calc(p);
    }

    @Override
    public void calc(Passport p) {
        String str = p.getField(field);
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                counter++;
            } else {
                counter = 0;
            }
        }
        this.letterRepeat = counter;
    }

    @Override
    public Integer getValue() {
        return letterRepeat;
    }
}
