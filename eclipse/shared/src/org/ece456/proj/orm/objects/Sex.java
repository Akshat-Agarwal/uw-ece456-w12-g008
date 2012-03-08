package org.ece456.proj.orm.objects;

public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    private String friendly;

    private Sex(String friendly) {
        this.friendly = friendly;
    }

    @Override
    public String toString() {
        return friendly;
    }
}
