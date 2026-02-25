package com.project.bank.main;

import com.project.bank.entity.Transaction;
import com.project.bank.entity.User;
import com.project.bank.service.UserService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
            System.out.println("1. Create Customer\n2. View Users\n3. See all Transactions\n4. View and Approve Chequebook Requests\n5. Exit");
            int selectedChoice = sc.nextInt();
            switch (selectedChoice) {
                case 1:
                    main.addUser();
                    break;
                case 2:
                    userService.printUsers();
                    break;
                case 3:
                    System.out.println("Enter userId :");
                    userService.checkTransactions(sc.next());
                    break;
                case 4:
                    main.viewRequests();
                    main.approveRequest();
                    break;
                case 5:
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
            System.out.println("1. Check Bank Balance\n2. Withdraw Amount\n3. Deposit Amount\n4. Fund Transfer\n5. Check Transaction History\n6. Raise Chequebook Request \n7. Exit");
            int selectedChoice = sc.nextInt();
            switch (selectedChoice) {
                case 1:
                    main.checkBankBalance(user.getUsername());
                    break;
                case 2:
                    main.withdrawAmount(user.getUsername());
                    break;
                case 3:
                    main.depositAmount(user.getUsername());
                    break;
                case 4:
                    main.fundTransfer(user.getUsername());
                    break;
                case 5:
                    userService.checkTransactions(user.getUsername());
                    break;
                case 6:
                    main.raiseChequebookRequest(user.getUsername());
                    break;
                case 7:
                    flag=false;
                    break;
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

    private void withdrawAmount(String username){
        System.out.println("Enter amount to withdraw :");
        double amt=sc.nextDouble();
        System.out.println("Enter password");
        String pass=sc.next();
        Double balance=userService.checkBankBalance(username);

        if(amt <= balance && userService.checkPass(username, pass)){
            balance=amt-balance;
            System.out.println("Withdraw Successful");
            userService.updateBalance(username,balance);
        }
        else if(amt>balance) {
            System.out.println("Insufficient balance");
        }
        else {
            System.out.println("Wrong Password");
        }
    }
    private void depositAmount(String username){
        System.out.println("Enter amount to deposit");
        double amt=sc.nextDouble();
        Double Balance= userService.checkBankBalance(username);
        Balance=Balance+amt;
        userService.updateBalance(username,Balance);
        System.out.println("Amount Deposited");
    }
    private void fundTransfer(String username){
        System.out.println("Enter payee ID : ");
        String userId=sc.next();
        User user=getUser(userId);
        if(user!=null){
            System.out.println("Enter amount to transfer");
            double amt=sc.nextDouble();
            double userBalance= userService.checkBankBalance(username);
            double payerBalance=userService.checkBankBalance(userId);
            if(amt <= userBalance){
                double finalBalance=userBalance-amt;
                userService.addTransaction(LocalDate.now(),username ,amt,userBalance,finalBalance,"debit");
                userService.addTransaction(LocalDate.now(),userId ,amt,payerBalance,payerBalance+amt,"credit");
                userService.updateBalance(username, finalBalance);
                userService.updateBalance(userId,payerBalance+amt);
                System.out.println("Funds Transferred Successfully");
            }
            else {
                System.out.println("Insufficient Bank Balance "+userBalance);
            }
        }
        else {
            System.out.println("Invalid Username");
        }
    }
    private void raiseChequebookRequest(String username){
        HashMap<String,Boolean> map=getAllRequests();
        if(map.containsKey(username) && map.get(username)){
            System.out.println("Request already raised and is approved");
        }
        else if(map.containsKey(username) && !map.get(username)){
            System.out.println("Request already raised and status is pending");
        }
        else{
            userService.raiseChequebookRequest(username);
            System.out.println("Request raised Successfully");
        }
    }
    private HashMap<String,Boolean> getAllRequests(){
        return userService.getAllRequests();
    }
    private User getUser(String userId){
        return userService.getUser(userId);
    }
    private void viewRequests(){
        HashMap<String,Boolean> requests = getAllRequests();
        System.out.println("UserId       "+ "Status");
        for (Map.Entry<String,Boolean> entry : requests.entrySet()){
            System.out.println(entry.getKey() +"        "+entry.getValue());
        }
    }
    private void approveRequest(){
        System.out.println("Enter userID to approve request");
        String username=sc.next();
        userService.approveRequest(username);
    }
}
