package org.ece456.proj.gui.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.ece456.proj.orm.objects.UserRole;
import org.ece456.proj.server.ServerRmi;
import org.ece456.proj.shared.Connection;
import org.ece456.proj.shared.ServerConstants;
import org.ece456.proj.shared.Session;

public class RmiClient {

    public Connection connect(String host, UserRole role, String username, String password) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, ServerConstants.SERVER_PORT);
            ServerRmi rmi = (ServerRmi) registry.lookup(ServerConstants.SERVICE);
            Session session = rmi.login(role, username, password);
            return new Connection(rmi, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
