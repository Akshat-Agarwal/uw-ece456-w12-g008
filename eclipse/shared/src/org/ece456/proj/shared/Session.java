package org.ece456.proj.shared;

import java.io.Serializable;

import org.ece456.proj.orm.objects.Id;
import org.ece456.proj.orm.objects.UserRole;

import com.google.common.base.Objects;

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Session INVALID_SESSION = new Session(UserRole.PATIENT, Id.invalidId(), -1L);

    private long sessionId;

    private final UserRole role;

    private final Id<?> id;

    public Session(UserRole role, Id<?> id, long sessionId) {
        this.role = role;
        this.id = id;
        setSessionId(sessionId);
    }

    public UserRole getRole() {
        return role;
    }

    public Id<?> getId() {
        return id;
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
                && Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sessionId, role, id);
    }
}