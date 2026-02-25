package com.project.bank.service;

import com.project.bank.entity.User;
import com.project.bank.repository.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;

public class UserService {
    private final UserRepository userRepository =new UserRepository();
    public  void printUsers(){
        userRepository.printUsers();
    }
    public void checkTransactions(String username){
        userRepository.checkTransactions(username);
    }
    public User login(String username, String password){
        return  userRepository.login(username,password);
    }
    public boolean addUser(String username, String password, String contactNo){
        return userRepository.addUser(username, password, contactNo);
    }
    public Double checkBankBalance(String username){
        return userRepository.checkBankBalance(username);
    }
    public boolean checkPass(String username , String pass){
        return userRepository.checkPass(username,pass);
    }
    public void updateBalance(String username, double balance){
        userRepository.updateBalance(username , balance);
    }
    public User getUser(String userId){
        return userRepository.getUser(userId);
    }
    public void addTransaction(LocalDate transactionDate,String transactionUserId,double transactionAmount,double initBalance,double finalBalance,String type){
        userRepository.addTransaction(transactionDate,transactionUserId,transactionAmount,initBalance,finalBalance,type);
    }
    public void raiseChequebookRequest(String username){
        userRepository.raiseChequebookRequest(username);
    }
    public HashMap<String,Boolean> getAllRequests(){
        return userRepository.getAllRequests();
    }
}
