package org.ece456.proj.orm.objects;

public enum Sex {
    MALE,
    FEMALE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
