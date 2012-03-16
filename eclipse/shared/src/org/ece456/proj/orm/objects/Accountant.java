package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class Accountant implements Serializable {
    private static final long serialVersionUID = 1L;

    private Id<Accountant> financeId;
    private String name;
    private String password;

    public Id<Accountant> getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Id<Accountant> setFinanceId) {
        this.financeId = setFinanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
