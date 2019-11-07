package io.turntabl;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ClientController clientController = new ClientController();

    public static void mainMenu() {

        System.out.println("----------------Welcome to Turntabl Client Management System(TCMS)--------------");
        System.out.println();
        System.out.println("1. To add new client details, enter '1' ");
        System.out.println();
        System.out.println("2. To view all clients, enter '2' ");
        System.out.println();
        System.out.println("3. To search, update and delete a client, enter '3' ");
        System.out.println();
        System.out.println("4. To exit, enter '4'");
        System.out.println();

    }

    public static void optionToAdd() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------Welcome to Turntabl Client Management System(TCMS)--------------");
        System.out.println();
        System.out.println("Enter Client Name: ");
        String name  = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Address: ");
        String address  = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Telephone: ");
        String telephone = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's email: ");
        String email  = scanner.nextLine();
        System.out.println();

        Client client = new Client(name,address,telephone,email);

        Map<String, String> response =  clientController.addNewClient(client);
        if (response.get("code").equals("00")){
            System.out.println(response.get("msg"));
        }else{
            System.out.println("Oops!!!, something went wrong, try again later");
        }


    }

    public static String optionToEnterClientId() {
        System.out.println();
        System.out.println("Enter the Client's ID to select client");
        return scanner.nextLine();

    }

    public static void optionToDeleteClient() {
        System.out.println();
        System.out.println("Are you sure you want to delete client? y/n:  ");
        System.out.println();
        System.out.println("Enter '1' to confirm  ");
        System.out.println();
        System.out.println("Enter '2' to decline" );

    }

    public static void optionToUpdateClient() {
        System.out.println();
        System.out.println("Enter '1' to change Name");
        System.out.println();
        System.out.println("Enter '2' to change Address");
        System.out.println();
        System.out.println("Enter '3' to change Telephone number");
        System.out.println();
        System.out.println("Enter '4' to change Email Address");
        System.out.println();
        System.out.println("Enter '5' to go to main menu");

    }

    public static void optionToDeleteUpdate() {
        System.out.println();
        System.out.println("Enter '1' to delete client");
        System.out.println();
        System.out.println("Enter '2' to update client's details");

    }


    public static String optionToChangeClientName() {
        System.out.println();
        System.out.println("Enter Client's New name: ");
        return scanner.nextLine();

    }
    public static String optionToChangeClientAddress() {
        System.out.println();
        System.out.println("Enter Client's New Address: ");
        return scanner.nextLine();

    }
    public static String optionToChangeClientTelephone() {
        System.out.println();
        System.out.println("Enter Client's New Telephone number: ");
        return scanner.nextLine();

    }
    public static String optionToChangeClientEmail() {
        System.out.println();
        System.out.println("Enter Client's New Email: ");
        return scanner.nextLine();

    }

    public static String menuToEnterClientName() {
        System.out.println("---------------Search, Update and delete Client's details menu -----------");
        System.out.println("Enter Client's name: ");
        return scanner.nextLine();

    }
    public static List<Client> searchClient(){
        String searchClient = menuToEnterClientName();
        String lowerName = searchClient.toLowerCase();
        List<Client> clients = clientController.getAllClients();
        return clients.stream().filter(c -> c.getName().toLowerCase().startsWith(lowerName)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        mainMenu();
        while (true){
            String response = scanner.nextLine();
            try {
                int actualResponse = Integer.parseInt(response);
                if(actualResponse < 1){
                    System.out.println("Input not valid option");
                }
                else if(actualResponse == 2){
                    List<Client> clientDetails = clientController.getAllClients();
                    System.out.println(clientDetails);
                    mainMenu();
                }
                else if(actualResponse == 3){
                    List<Client> clientName = searchClient();
                    System.out.println(clientName);
                    optionToDeleteUpdate();
                    while (true) {
                        String updateOption = scanner.nextLine();
                        try{
                            int actualUpdateOption = Integer.parseInt(updateOption);
                            if(actualUpdateOption == 1){
                                optionToDeleteClient();
                                String deleteOption = scanner.nextLine();
                                int actualDeleteOption = Integer.parseInt(deleteOption);
                                if(actualDeleteOption == 1) {
                                    System.out.println("Client deleted successfully!!");
                                    mainMenu();
                                    break;
                                }
                                else if(actualDeleteOption == 0){
                                    System.out.println("Client not deleted!!");
                                    mainMenu();
                                    break;
                                }
                                else{
                                    System.out.println("Enter a valid option");
                                }
                            }
                            else if(actualUpdateOption == 2){
                                String clientId = optionToEnterClientId();
                                optionToUpdateClient();
                                String updateOptionResponse = scanner.nextLine();
                                int updateOptionResponse1 = Integer.parseInt(updateOptionResponse);
                                if(updateOptionResponse1 == 1){
                                    String newClientName = optionToChangeClientName();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", newClientName);
                                    mapOfNames.put("address", "");
                                    mapOfNames.put("phoneNumber", "");
                                    mapOfNames.put("email", "");
                                    mapOfNames.put("id", clientId);
                                    Map<String, String> mapOfErrorCodes = clientController.updateClient(mapOfNames);
                                    if (mapOfErrorCodes.get("code").equals("00")){
                                         System.out.println(mapOfErrorCodes.get("msg"));
                                    }else{
                                        System.out.println(mapOfErrorCodes.get("msg"));
                                    }
                                }
                                mainMenu();
                                break;

                            }
                            else if(actualUpdateOption > 2){
                                System.out.println("Please enter a valid option! ");
                            }

                        }catch(NumberFormatException exception){
                            System.out.println("Input not number");
                        }
                    }

                }
                else if(actualResponse == 4){
                    break;
                }

            }
            catch(NumberFormatException exception){
                System.out.println("Input not number");

            }

        }


    }

}
