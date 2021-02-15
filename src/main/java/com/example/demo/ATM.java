package com.example.demo;

import java.util.Scanner;

public class ATM {
    private Account session;
    private boolean sessionStatus;
    private BankService bankService;

    public ATM(BankService bankService) {
        this.bankService = bankService;
        this.session = null;
        this.sessionStatus = false;
    }


    public void start() {

        Scanner sc = new Scanner(System.in);

        String username, pin;
        while (!sessionStatus) {
            System.out.println("Please log in:\n");
            username = sc.nextLine();
            pin = sc.nextLine();
            if (bankService.validate(username, pin)) {
                sessionStatus = true;
                session = ((Bank) bankService).getAccount(username);
            }
        }
        int choise = -1;
        while (choise != 0) {
            System.out.println("write 0 to exit");
            System.out.println("write 2 to topUp");
            System.out.println("write 3 to withdraw");
            System.out.println("write 4 to check balance");
            choise = sc.nextInt();
            Double choise1;
            switch (choise) {
                case 2:
                    System.out.println("write num:");

                    choise1 = sc.nextDouble();
                    if (bankService.topUp(choise1, session.getUsername())) {
                        System.out.println("Succes");

                    } else System.out.println("something went wrong");


                    break;
                case 3:

                    System.out.println("write num:");

                    choise1 = sc.nextDouble();

                    if (bankService.withdraw(choise1, session.getUsername())) {
                        System.out.println("Succes");
                    } else System.out.println("Error not enough");

                    break;
                case 4:


                    System.out.println(bankService.checkBalance(session.getUsername()));

                    break;

            }


        }


    }


}
