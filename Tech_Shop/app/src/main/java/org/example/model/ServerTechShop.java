package org.example.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.example.controller.ControllerProducts;

public class ServerTechShop {
    
    private ExecutorService pool;
    private ServerSocket serverSocket;
    private ControllerProducts data;

    public ServerTechShop(ExecutorService pool, ServerSocket serverSocket) {
        this.pool = pool;
        this.serverSocket = serverSocket;
        this.data = new ControllerProducts();
        data.generateManualProducts();
    }

    public void execute() {
        try {
            if (pool == null) this.pool = Executors.newFixedThreadPool(3);
            if (serverSocket == null) this.serverSocket = new ServerSocket(5000);

            System.out.println("Server is ready and waiting for connections...");

            while (true) {
                pool.execute(new ClientsHandler(serverSocket.accept(), data));
            }

        } catch (IOException e) {
            pool.shutdown();
            System.out.println("Surgio un error: " + e.getMessage());
        }
    }
}
