package com.project.bank.repository;

import com.project.bank.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRepository {
    static  Set<User> users=new HashSet<>();
    static {
        User user1= new User("admin","admin","1234567","admin",0);
        User user2= new User("user2","user2","12345678","user",500);
        User user3= new User("user3","user3","12345679","user",500);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    public  void  printUsers(){
        for(User user: users){
            if(user.getRole().equals("admin"))continue;
            System.out.println("Username :"+user.getUsername()+" || Password :"+user.getPassword()+" || Contact Number :"+user.getContactNo()+" || Account Balance :"+user.getAccountBalance());
        }
    }
    public User login(String username,String password){
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public boolean addUser(String username, String password, String contactNo){
        User user = new User(username,password,contactNo,"user",500.00);
        return users.add(user);
    }
    public Double checkBankBalance(String username){
        for(User user: users){
            if(user.getUsername().equals(username)) return user.getAccountBalance();
        }
        return null;
    }
    public boolean checkPass(String username, String pass){
        for(User user:users){
            if(user.getUsername().equals(username) && user.getPassword().equals(pass))return true;
        }
        return false;
    }
    public void updateBalance(String username, double balance){
        for(User user:users){
            if(user.getUsername().equals(username)){
                user.setAccountBalance(balance);
            }
        }
    }
    public User getUser(String userId){
        for(User user: users) if (user.getUsername().equals(userId)) return user;
        return null;
    }
}
