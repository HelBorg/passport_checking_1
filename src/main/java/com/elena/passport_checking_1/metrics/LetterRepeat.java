package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public abstract class LetterRepete extends FieldMetrics<String> {
    private Integer letterRepeat;
    public String field;

    protected LetterRepeate(Passport p) {
        this.calc(p);
    }

    @Override
    public void calc(Passport p) {
        String str = getFieldValue(p);
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
