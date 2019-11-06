package io.turntabl;

import java.util.Scanner;


public class Main {

    public static void menu(){

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
    public static void input1(){

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
        System.out. println("Client with the following details added successfully");
        System.out.println("Client's name: " + name);
        System.out.println("Client's Address: " + address);
        System.out.println("Client's Telephone: " + telephone);
        System.out.println("Client's Email: " + email);
        System.out.println();


    }
    public static void input3(){
        System.out.println("----------------Welcome to Turntabl Client Management System(TCMS)--------------");
        System.out.println();
        System.out.println("Enter Client's name: ");


    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        menu();
        while(true) {
            String response = scanner.nextLine();
            try {
                int actualResponse = Integer.parseInt(response);
                if (actualResponse > 4) {
                    System.out.println("Please enter a valid option ");
                } else if (actualResponse == 1) {
                    input1();
                } else if(actualResponse == 3){
                    input3();
                }
                else if (actualResponse == 4) {
                    break;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Input is not an integer");

            }
        }

        }






    }
