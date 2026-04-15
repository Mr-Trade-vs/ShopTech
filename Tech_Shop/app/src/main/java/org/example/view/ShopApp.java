package org.example.view;

import java.util.Scanner;

import org.example.controller.ControllerCommunication;
import org.example.controller.ControllerProducts;

public class ShopApp {

    public static void main(String[] args) {
    
        ControllerCommunication controllerCommunication = new ControllerCommunication();
        ControllerProducts controllerProducts = new ControllerProducts();
        ShopApp main = new ShopApp();

        Scanner rd = new Scanner(System.in);

        int opt;

        System.out.println("¿Que papel vas a desempeñar?\n" +
                            "[1] Servidor\n" + 
                            "[2] Cliente");

        opt = rd.nextInt();
        rd.nextLine();

        switch (opt) {
            case 1:

                System.out.println("¿Cuantas conexiones quiere tener?");
                int connections = rd.nextInt();
                rd.nextLine();
                main.menuServer(controllerCommunication, controllerProducts, connections);
                break;
                
            case 2:
                main.menuClient(controllerCommunication);
                break;

            default:
                System.out.println("Opción invalida, intenta nuevamente");
                break;
        }
    }

    public void menuServer(ControllerCommunication controller, ControllerProducts data, int connections) {

        //Inicializating data Products
        data.generateManualProducts();

        System.out.println("System receiving messages...");
        controller.startCommunicationServer(connections);
        System.out.println("Exiting communication...");
        
    }

    public void menuClient(ControllerCommunication controller) {

        System.out.println("Sending messages to server...");
        controller.startClient();

    }
}
