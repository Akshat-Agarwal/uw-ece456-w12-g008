package org.ece456.proj.shared;

import org.ece456.proj.server.ServerRmi;

public class Connection {
    private final ServerRmi server;

    public Connection(ServerRmi server, Session session) {
        this.server = server;
        this.session = session;
    }

    private final Session session;

    public ServerRmi getServer() {
        return server;
    }

    public Session getSession() {
        return session;
    }
}
