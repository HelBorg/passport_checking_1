package com.elena.passport_checking_1.metrics;

public class WordMetrics {
    private int len;
    private int vowel;
    private int consonant;
    private int letterRepeat;

    public void updateWordMetrics(String string) {
        this.len = string.length();
        this.vowel = string.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
        this.consonant = string.length() - this.vowel;
        updateLetterRepeat(string);
    }

    public void updateLen(String string) {
        this.len = string.length();
    }

    public void updateVowel(String string) {
        this.vowel = string.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
    }

    public void updateConsonant(String string) {
        this.consonant = string.length() - string.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
    }

    public void updateLetterRepeat(String string) {
        int counter = 0;
        for (int i = 0; i < len; i++) {
            if (string.charAt(i) == string.charAt(i + 1)) {
                counter++;
            } else {
                counter = 0;
            }
        }
        this.letterRepeat = counter;
    }

    public int getLen() {
        return len;
    }

    public int getVowel() {
        return vowel;
    }

    public int getConsonant() {
        return consonant;
    }

    public int getLetterRepeat() {
        return letterRepeat;
    }
}
