package com.elena.passport_checking_1.Model;

public class Word {
    private String word;
    private int len;
    private int vowel;
    private int consonant;

    public String getWord() {
        return word;
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

    public void setWord(String word) {
        this.word = word;
        this.updateLen();
        this.updateVowel();
        this.updateConsonant();
    }

    private void updateLen() {
        this.len = word.length();
    }

    private void updateVowel() {
        this.vowel = this.word.toLowerCase()
                .replaceAll("а|о|е|и|у|я", "").length();
    }

    private void updateConsonant() {
        this.consonant = this.word.length() -
                this.word.toLowerCase().replaceAll("а|о|е|и|у|я", "").length();
    }
}
