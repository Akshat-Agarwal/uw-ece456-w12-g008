package org.ece456.proj.server.authentication;

import java.util.concurrent.TimeUnit;

import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.shared.Session;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public enum SessionManager {
    INSTANCE; // enum singleton pattern

    private static long generateSessionId() {
        return (long) (Math.random() * Long.MAX_VALUE);
    }

    private final Cache<Long, Session> sessions = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.HOURS).build();

    public Session getNewSession(UserRole role, String username) {
        long sessionId = generateSessionId();
        Session session = new Session(role, username, sessionId);

        sessions.put(sessionId, session);

        return session;
    }

    public void invalidate(Session session) {
        sessions.invalidate(session.getSessionId());
    }

    public boolean isValidSession(Session session) {
        return sessions.getIfPresent(session.getSessionId()) != null;
    }
}
