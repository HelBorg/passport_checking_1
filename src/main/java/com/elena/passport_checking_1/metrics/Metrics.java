package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;

import java.util.ArrayList;
import java.util.List;


public class Metrics {
    private List<Metric> metrics = new ArrayList<Metric>();

    public void updateMetrics(Passport p) {
        List<String> fields = new ArrayList();
        fields.add("FirstName");
        fields.add("SecondName");
        fields.add("LastName");

        for (String s : fields) {
            metrics.add(new Consonants(p, s));
            metrics.add(new Vowels(p, s));
            metrics.add(new LetterRepeate(p, s));
            metrics.add(new LenString(p, s));
        }

        metrics.add(new LenNumber( p,"Number"));
        metrics.add(new LenNumber(p, "Series"));

    }
}

