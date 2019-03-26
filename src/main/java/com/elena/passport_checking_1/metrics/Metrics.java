package com.elena.passport_checking_1.metrics;

<<<<<<< master
public class Metrics {
    private WordMetrics firstNameMetrics;
    private WordMetrics secondNameMetrics;
    private WordMetrics lastNameMetrics;
=======
import com.elena.passport_checking_1.model.Passport;

import java.util.ArrayList;
import java.util.List;


public class Metrics {
    private List<Metric> metrics = new ArrayList<Metric>();
>>>>>>> local

    public void updateFNM(String firstName) {
        firstNameMetrics.updateWordMetrics(firstName);
    }

    public void updateSNM(String secondName) {
        secondNameMetrics.updateWordMetrics(secondName);
    }

<<<<<<< master
    public void updateLNM(String lastName) {
        lastNameMetrics.updateWordMetrics(lastName);
=======
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

>>>>>>> local
    }
}

