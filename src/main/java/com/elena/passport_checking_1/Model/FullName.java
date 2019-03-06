package com.elena.passport_checking_1.Model;

public class FullName {
    private Word firstName;
    private Word secondName;
    private Word lastName;

    public FullName(Word firstName, Word secondName, Word lastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public Word getFirstName() {
        return firstName;
    }

    public Word getSecondName() {
        return secondName;
    }

    public Word getLastName() {
        return lastName;
    }

    public void setFirstName(Word firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(Word secondName) {
        this.secondName = secondName;
    }

    public void setLastName(Word lastName) {
        this.lastName = lastName;
    }
}

