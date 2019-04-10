package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public abstract class FieldMetrics<T> implements Metric {

    abstract T getFieldValue(Passport p);
}
