package com.project.bank.main;

import com.project.bank.entity.User;
import com.project.bank.service.UserService;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static UserService userService = new UserService();
    static Main main = new Main();
    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter Username");
            String username = sc.next();
            System.out.println("Enter Password");
            String password = sc.next();

            User user = userService.login(username, password);
            if (user != null && user.getRole().equals("admin")) {
                initAdmin(user);
            } else if (user != null && user.getRole().equals("user")) {
                initUser(user);
            } else {
                System.out.println("Login failed");
            }
        }
    }

    private static void initAdmin(User user) {
        System.out.println("Welcome Admin");
        boolean flag = true;
        while (flag) {
            System.out.println("1. Create Customer\n2. View Users\n3. Exit");
            int selectedChoice = sc.nextInt();
            switch (selectedChoice) {
                case 1:
                    main.addUser();
                    break;
                case 2:
                    userService.printUsers();
                    break;
                case 3:
                    flag = false;
                    System.out.println("Logged out Successfully...");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static void initUser(User user) {
        System.out.println("Welcome " + user.getUsername());
        boolean flag = true;
        while (flag) {
            System.out.println("1. Check Bank Balance\n2. Withdraw Amount\n3. Deposit Amount\n4. Exit");
            int selectedChoice = sc.nextInt();
            switch (selectedChoice) {
                case 1:
                    main.checkBankBalance(user.getUsername());
            }
        }
    }
    private void addUser(){
        System.out.println("Username :");
        String username = sc.next();
        System.out.println("Password :");
        String password = sc.next();
        System.out.println("Enter Contact Number :");
        String contactNumber = sc.next();
        boolean result = userService.addUser(username, password, contactNumber);
        if (result) {
            System.out.println("User added successfully..");
        } else {
            System.out.println("User addition failed");
        }
    }
    private void checkBankBalance(String username){
        Double balance=userService.checkBankBalance(username);
        if(balance!=null) {
            System.out.println("Current Balance = " + balance);
        }
        else System.out.println("Check the username");
    }

}
