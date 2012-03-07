package org.ece456.proj.orm.objects;

import java.io.Serializable;

public class PatientContact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private String phoneNumber;
    private long passwordHash;

    public PatientContact() {
        clear();
    }

    public void clear() {
        name = "";
        address = "";
        phoneNumber = "";
        passwordHash = 0L;
    }

    // getters and setters below

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNumber;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNumber = phoneNum;
    }

    public long getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(long passwordHash) {
        this.passwordHash = passwordHash;
    }
}