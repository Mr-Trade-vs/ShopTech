package org.example.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTechShop {
    
    private ExecutorService pool;
    private ServerSocket serverSocket;

    public ServerTechShop(ExecutorService pool, ServerSocket serverSocket) {
        this.pool = pool;
        this.serverSocket = serverSocket;
    }

    public void execute() {

        try {
            if (pool == null) this.pool = Executors.newFixedThreadPool(3);
            if (serverSocket == null) this.serverSocket = new ServerSocket(5000);

            pool.execute(new ClientsHandler(serverSocket.accept()));

        } catch (IOException e) {
            pool.shutdown();
            System.out.println("Surgio un error: " + e.getMessage());
        }
        
    }
}
