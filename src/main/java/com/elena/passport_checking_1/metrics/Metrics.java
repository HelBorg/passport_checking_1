package com.elena.passport_checking_1.metrics;

import com.elena.passport_checking_1.model.Passport;
import org.elasticsearch.action.get.GetResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Metrics {
    private List<Metric> metrics = new ArrayList<Metric>();

    public Metrics(Passport p) {
        updateMetrics(p);
    }

    public Metrics(Map<String, Object> map) {

        //Metrics for first name
        metrics.add(new LenString((int) map.get("0")){
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });
        metrics.add(new Consonants((int) map.get("1")) {
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });
        metrics.add(new Vowels((int) map.get("2")) {
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });
        metrics.add(new LetterRepeat((int) map.get("3")) {
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });


        //Metrics for second name
        metrics.add(new LenString((int) map.get("4")) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });
        metrics.add(new Consonants((int) map.get("5")) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });
        metrics.add(new Vowels((int) map.get("6")) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });
        metrics.add(new LetterRepeat((int) map.get("7")) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });


        //Metrics for last name
        metrics.add(new LenString((int) map.get("8")) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });
        metrics.add(new Consonants((int) map.get("9")) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });
        metrics.add(new Vowels((int) map.get("10")) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });
        metrics.add(new LetterRepeat((int) map.get("11")) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });


        metrics.add(new AgeOfGetting((int) map.get("12")));

        //Metrics for number
        metrics.add(new LenNumber((int) map.get("13")) {
            Integer getFieldValue(Passport p) {
                return p.getNumber();
            }
        });

        //Metrics for series
        metrics.add(new LenNumber((int) map.get("14")) {
            Integer getFieldValue(Passport p) {
                return p.getSeries();
            }
        });

    }

    public void updateMetrics(Passport p) {
        //Metrics for first name
        metrics.add(new LenString(p) {
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });
        metrics.add(new Consonants(p) {
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });
        metrics.add(new Vowels(p) {
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });
        metrics.add(new LetterRepeat(p) {
            String getFieldValue(Passport p) {
                return p.getFirstName();
            }
        });


        //Metrics for second name
        metrics.add(new LenString(p) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });
        metrics.add(new Consonants(p) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });
        metrics.add(new Vowels(p) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });
        metrics.add(new LetterRepeat(p) {
            String getFieldValue(Passport p) {
                return p.getSecondName();
            }
        });


        //Metrics for last name
        metrics.add(new LenString(p) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });
        metrics.add(new Consonants(p) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });
        metrics.add(new Vowels(p) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });
        metrics.add(new LetterRepeat(p) {
            String getFieldValue(Passport p) {
                return p.getLastName();
            }
        });


        metrics.add(new AgeOfGetting(p));

        //Metrics for number
        metrics.add(new LenNumber(p) {
            Integer getFieldValue(Passport p) {
                return p.getNumber();
            }
        });

        //Metrics for series
        metrics.add(new LenNumber(p) {
            Integer getFieldValue(Passport p) {
                return p.getSeries();
            }
        });
    }

    public List<Metric> getMetrics() {
        return metrics;
    }
}

