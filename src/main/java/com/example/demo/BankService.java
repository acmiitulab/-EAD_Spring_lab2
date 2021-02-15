package com.example.demo;


public interface BankService {
    double checkBalance(String cardNumber);

    boolean withdraw(double amount, String cardNumber);

    boolean topUp(double amount, String cardNumber);

    boolean validate(String username, String pinCode);
}
