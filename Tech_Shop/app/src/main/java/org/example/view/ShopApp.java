package org.example.view;

import java.util.Scanner;

import org.example.controller.ControllerCommunication;

public class ShopApp {

    public static void main(String[] args) {
    
        ControllerCommunication controllerCommunication = new ControllerCommunication();
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
                main.menuServer(controllerCommunication, connections);
                break;
                
            case 2:
                System.out.println("Enter server IP address:");
                String ip = rd.nextLine();
                main.menuClient(controllerCommunication, rd, ip);
                break;

            default:
                System.out.println("Opcion invalida, intenta nuevamente");
                break;
        }

        rd.close();
    }

    public void menuServer(ControllerCommunication controller, int connections) {
        System.out.println("System receiving messages...");
        controller.startCommunicationServer(connections);
    }

    public void menuClient(ControllerCommunication controller, Scanner rd, String ip) {

        controller.startClient(ip);

        boolean active = true;

        while (active) {

            System.out.println("__________________________________\n" + 
                               "What do you want to do?\n" +
                               "[1] See all products\n" +
                               "[2] Search a product\n" +
                               "[3] Buy a product\n" +
                               "[4] Exit\n" +
                               "__________________________________");

            int option = rd.nextInt();
            rd.nextLine();

            switch (option) {

                case 1:
                    controller.sendToServer("1");
                    System.out.println(readFullResponse(controller));
                    break;

                case 2:
                    controller.sendToServer("2");
                    System.out.println(controller.receiveFromServer());

                    String searchId = rd.nextLine();
                    controller.sendToServer(searchId);
                    System.out.println(readFullResponse(controller));
                    break;

                case 3:
                    controller.sendToServer("3");
                    System.out.println(controller.receiveFromServer());

                    String buyId = rd.nextLine();
                    controller.sendToServer(buyId);

                    System.out.println(readFullResponse(controller));
                    System.out.println(controller.receiveFromServer());

                    String qty = rd.nextLine();
                    controller.sendToServer(qty);

                    System.out.println(controller.receiveFromServer());

                    String confirm = rd.nextLine();
                    controller.sendToServer(confirm);

                    System.out.println(controller.receiveFromServer());
                    break;

                case 4:
                    controller.sendToServer("EXIT");
                    controller.endClient();
                    active = false;
                    System.out.println("Disconnected from server. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
    }

    private String readFullResponse(ControllerCommunication controller) {
        String result = "";
        String line = "";
        while ((line = controller.receiveFromServer()) != null && !line.isEmpty()) {
            result += line + "\n";
        }
        return result;
    }
}
