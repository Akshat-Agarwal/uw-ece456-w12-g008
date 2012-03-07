package org.ece456.proj.shared;

import java.io.Serializable;

import org.ece456.proj.orm.objects.UserRole;

import com.google.common.base.Objects;

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Session INVALID_SESSION = new Session(UserRole.PATIENT, "", -1L);

    private long sessionId;

    private final UserRole role;

    private final String username;

    public Session(UserRole role, String username, long sessionId) {
        this.role = role;
        this.username = username;
        setSessionId(sessionId);
    }

    public UserRole getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public long getSessionId() {
        return sessionId;
    }

    public boolean isValid() {
        return !INVALID_SESSION.equals(this);
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Session)) {
            return false;
        }
        Session that = (Session) obj;
        return Objects.equal(sessionId, that.sessionId) && Objects.equal(role, that.role)
                && Objects.equal(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sessionId, role, username);
    }
}