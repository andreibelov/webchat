package net.bootlab.webchat.enums;

public enum Sex {

    MALE("male"),
    FEMALE("female"),
    UNDEFINED("undefined");

    Sex(final String gender) {
        this.gender = gender;
    }

    private String gender;

    @Override
    public String toString() {
        return gender;
    }
}