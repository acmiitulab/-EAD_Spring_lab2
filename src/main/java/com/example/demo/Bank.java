package com.example.demo;


import java.util.ArrayList;

public class Bank implements BankService {
    private DatabaseConnection accounts;

    public Bank(DatabaseConnection accounts) {
        this.accounts = accounts;
    }

    public Account getAccount(String username) {

        return accounts.getAccount(username);
    }


    public boolean validate(String username, String pinCode) {
        Account account = accounts.getAccount(username);
        if (account == null) {
            System.out.println("Not found");
            return false;
        }
        if (account.getPin().equals(pinCode)) return true;

        return false;
    }


    // BankService methods
    @Override
    public double checkBalance(String username) {
        Account account = getAccount(username);

        return account == null ? 0 : account.getBalance();
    }

    @Override
    public boolean withdraw(double amount, String username) {
        Account account = getAccount(username);


        if (account == null || account.getBalance() - amount < 0) {
            return false;
        }

        account.setBalance(account.getBalance() - amount);
        accounts.update(account);
        return true;
    }

    @Override
    public boolean topUp(double amount, String username) {
        Account account = getAccount(username);


        if (account == null) {
            return false;
        }

        account.setBalance(account.getBalance() + amount);
        accounts.update(account);
        return true;
    }


    public void changePinCode(Account account, String newPinCode) {
        account.setPin(newPinCode);
    }

    // Method toString


    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                '}';
    }
}
