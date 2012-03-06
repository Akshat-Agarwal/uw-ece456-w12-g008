package org.ece456.proj.orm.objects;

import java.io.Serializable;

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

    public int getId() {
        return id;
    }

    // Generated code below

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Id<?> other = (Id<?>) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
