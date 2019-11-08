package main.java.io.turntabl;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ClientController clientController = new ClientController();

    public static void mainMenu() {
        System.out.println("\033[0;94m  ************************************************************************************ \033[0m");
        System.out.println();
        System.out.println("* \t \t \033[0;92m Welcome to Turntabl Client Management System(TCMS) \033[0m");
        System.out.println();
        System.out.println("\033[0;94m ************************************************************************************ \033[0m");
        System.out.println();
        System.out.println("\033[0;93m 1. To add new client details, enter '1' ");
        System.out.println();
        System.out.println("2. To view all clients, enter '2' ");
        System.out.println();
        System.out.println("3. To search, update and delete a client, enter '3' ");
        System.out.println();
        System.out.println("4. To exit, enter '4'\033[0m");
        System.out.println();

    }

    public static void optionToAdd() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("---------------- \033[0;92m  TCMS Add new Client Menu \033[0m    --------------");
        System.out.println();
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("\033[0;93m Enter Client Name: ");
        String name  = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Address: ");
        String address  = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Telephone: ");
        String telephone = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's email: \033[0m");
        String email  = scanner.nextLine();
        System.out.println();

        Client client = new Client(name,address,telephone,email);
        System.out.println();
        Map<String, String> response =  clientController.addNewClient(client);
        if (response.get("code").equals("00")){
            System.out.println("\033[0;92m"+ response.get("msg") + "\033[0m");
        }else{
            System.out.println("\033[0;91m Oops!!!, something went wrong, try again later\033[0m");
        }


    }

    public static String optionToEnterClientId() {
        System.out.println();
        System.out.println("\033[0;93m Enter the Client's ID to select client \033[0m");
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
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("---------------- \033[0;92m  TCMS Update Client's detsils Menu \033[0m    --------------");
        System.out.println();
        System.out.println("\033[0;94m *************************************************************** \033[0m");
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
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("---------------- \033[0;92m  TCMS Search Menu \033[0m    --------------");
        System.out.println();
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
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
                    System.out.println("\033[0;91m Input not valid option\033[0m");
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
                                    String clientId = optionToEnterClientId();
                                    Map<String, String> errorCodes = clientController.removeClient(clientId);
                                    if (errorCodes.get("code").equals("00")){
                                        System.out.println();
                                        System.out.println("\033[0;92m"+ errorCodes.get("msg") + "\033[0m");
                                        System.out.println();
                                    }else{
                                        System.out.println(errorCodes.get("msg"));
                                    }
                                    mainMenu();
                                    break;
                                }
                                else if(actualDeleteOption == 0){
                                    System.out.println();
                                    System.out.println("\033[0;92mAborted!!\033[0m");
                                    System.out.println();
                                    mainMenu();
                                    break;
                                }
                                else{
                                    System.out.println();
                                    System.out.println("\033[0;91m Enter a valid option\033[0m");
                                    System.out.println();
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
                                else if(updateOptionResponse1 == 2){
                                    String newClientAddress = optionToChangeClientAddress();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", "");
                                    mapOfNames.put("address", newClientAddress);
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
                                else if(updateOptionResponse1 == 3){
                                    String newClientTelephone = optionToChangeClientTelephone();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", "");
                                    mapOfNames.put("address", "");
                                    mapOfNames.put("phoneNumber", newClientTelephone);
                                    mapOfNames.put("email", "");
                                    mapOfNames.put("id", clientId);
                                    Map<String, String> mapOfErrorCodes = clientController.updateClient(mapOfNames);
                                    if (mapOfErrorCodes.get("code").equals("00")){
                                        System.out.println(mapOfErrorCodes.get("msg"));
                                    }else{
                                        System.out.println(mapOfErrorCodes.get("msg"));
                                    }
                                }
                                else if(updateOptionResponse1 == 4){
                                    String newClientEmail = optionToChangeClientEmail();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", "");
                                    mapOfNames.put("address", "");
                                    mapOfNames.put("phoneNumber", "");
                                    mapOfNames.put("email", newClientEmail);
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
                                System.out.println("\033[0;91mPlease enter a valid option!\033[0m ");
                            }

                        }catch(NumberFormatException exception){
                            System.out.println("\033[0;91mInput not number\033[0m");
                        }
                    }

                }
                else if(actualResponse == 4){
                    break;
                }

            }
            catch(NumberFormatException exception){
                System.out.println("\033[0;91mInput not number\033[0m");

            }

        }


    }

}
