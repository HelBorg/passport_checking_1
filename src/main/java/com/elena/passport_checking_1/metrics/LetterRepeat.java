package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public abstract class LetterRepeat extends FieldMetrics<String> {
    private Integer letterRepeat;

    public LetterRepeat(Passport p) {
        this.calc(p);
    }

    public LetterRepeat(Integer letterRepeat) {
        this.letterRepeat = letterRepeat;
    }

    @Override
    public void calc(Passport p) {
        String str = getFieldValue(p);
        int counter = 1;

        int length = str.length();
        if(length > 0) {
            for (int i = 0; i < length - 1; i++) {
                if (str.charAt(i) == str.charAt(i + 1)) {
                    counter++;
                } else {
                    counter = 1;
                }
            }
        }
        this.letterRepeat = new Integer(counter);
    }

    @Override
    public Integer getValue() {
        return letterRepeat;
    }
}
