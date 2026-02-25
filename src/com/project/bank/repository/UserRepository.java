package com.project.bank.repository;

import com.project.bank.entity.Transaction;
import com.project.bank.entity.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserRepository {
    private static final Set<User> users=new HashSet<>();
    private  static final  Set<Transaction> transactions=new HashSet<>();
    HashMap<String,Boolean> chequebookRequests =new HashMap<>();
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
    public void approveRequest(String username){
        chequebookRequests.put(username, true);
    }
    public void checkTransactions(String username){
        for(Transaction transaction: transactions){
            if(transaction.transactionUserId().equals(username)){
                System.out.println("Transaction{" +
                        "transactionDate=" + transaction.transactionDate() +
                        ", transactionUserId='" + transaction.transactionUserId() + '\'' +
                        ", transactionAmount=" + transaction.transactionAmount() +
                        ", initialBalance=" + transaction.initialBalance() +
                        ", finalBalance=" + transaction.finalBalance() +
                        ", transactionType='" + transaction.transactionType() + '\'' +
                        '}');
            }
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
    public void addTransaction(LocalDate transactionDate, String transactionUserId, double transactionAmount, double initBalance, double finalBalance, String type){
        Transaction transaction=new Transaction(transactionDate,transactionUserId,transactionAmount,initBalance,finalBalance,type);
        transactions.add(transaction);
    }
    public void raiseChequebookRequest(String username){
        chequebookRequests.put(username,false);

    }

    public HashMap<String, Boolean> getAllRequests() {
        return chequebookRequests;
    }

}
