package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

public abstract class FieldMetrics<T> implements Metric {
    private T field;

    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
    }

    abstract void setField(Passport p);
}
