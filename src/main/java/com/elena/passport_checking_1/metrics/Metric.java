package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public interface Metric<T> {

    public void calc(Passport p);

    public T getValue();
}
