package com.elena.passport_checking_1.metrics;

public class Metrics {
    private WordMetrics firstNameMetrics;
    private WordMetrics secondNameMetrics;
    private WordMetrics lastNameMetrics;

    public void updateFNM(String firstName) {
        firstNameMetrics.updateWordMetrics(firstName);
    }

    public void updateSNM(String secondName) {
        secondNameMetrics.updateWordMetrics(secondName);
    }

    public void updateLNM(String lastName) {
        lastNameMetrics.updateWordMetrics(lastName);
    }
}

