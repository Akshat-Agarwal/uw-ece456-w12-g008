package org.ece456.proj.server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.ece456.proj.server.ServerRmi;
import org.ece456.proj.shared.ServerConstants;

public class ServerMain {

    private static ServerRmi server;

    public static void main(String[] args) throws UnknownHostException, RemoteException {
        // Initialize the system
        System.out.println("ECE 456 DB Server - - - - - - - - -");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
            System.out.println(ex);
        }

        System.out.println("Initialize RMI service...");
        server = new ServerRmiImpl();

        String address = InetAddress.getLocalHost().toString();

        System.out.printf("Server address is %s and the port is %d\n", address,
                ServerConstants.SERVER_PORT);

        // Create the registry and bind the name and object.
        Registry registry = LocateRegistry.createRegistry(ServerConstants.SERVER_PORT);
        registry.rebind(ServerConstants.SERVICE, server);

        System.out.println("DB server is ready to accept RMI requests.");
        System.out.println("Type 'quit' without quotes to exit CC server.");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                        System.in));
                while (true) {
                    String line = "";
                    try {
                        line = inFromUser.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (line.equalsIgnoreCase("quit")) {
                        System.out.println("Ending DB server...");
                        System.exit(0);
                    } else {
                        System.out.println("Type 'quit' wihtout quotes to exit the server.");
                    }
                }
            }
        });
        thread.run();
    }
}
