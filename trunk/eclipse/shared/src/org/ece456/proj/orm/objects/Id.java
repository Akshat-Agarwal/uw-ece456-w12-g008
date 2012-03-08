package org.ece456.proj.orm.objects;

import java.io.Serializable;

import com.google.common.base.Objects;

public class Id<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;

    public static <T> Id<T> of(int id) {
        return new Id<T>(id);
    }

    public static <T> Id<T> invalidId() {
        return of(-1);
    }

    private Id(int id) {
        this.id = id;
    }

    public int asInt() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Id)) {
            return false;
        }
        Id<?> that = (Id<?>) obj;
        return Objects.equal(id, that.id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
