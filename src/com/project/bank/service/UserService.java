package com.project.bank.service;

import com.project.bank.entity.User;
import com.project.bank.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository =new UserRepository();
    public  void printUsers(){
        userRepository.printUsers();
    }
    public User login(String username, String password){
        return  userRepository.login(username,password);
    }
}
