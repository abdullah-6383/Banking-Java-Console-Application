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
        User user2= new User("user2","user2","12345678","user",0);
        User user3= new User("user3","user3","12345679","user",0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    public  void  printUsers(){
        System.out.println(users);
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
}
