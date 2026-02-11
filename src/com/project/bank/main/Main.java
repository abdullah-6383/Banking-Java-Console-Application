package com.project.bank.main;

import com.project.bank.entity.User;
import com.project.bank.service.UserService;

import java.util.Scanner;

public class Main {
    static  Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        UserService userService=new UserService();
        System.out.println("Enter Username");
        String username = sc.next();
        System.out.println("Enter Password");
        String password= sc.next();
        userService.printUsers();
        User user= userService.login(username,password);
        if(user!=null){
            System.out.println("Logged in");
        }
        else {
            System.out.println("Login failed");
            return;
        }
    }
}
