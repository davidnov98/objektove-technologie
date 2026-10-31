package com.example.banka;

public class Employee {
    private static int counter = 0;
    private final int id;
    private BankAccount acc;

    public Employee(BankAccount b) {
        this.id = ++counter;
        this.acc = b;
    }

    public int getID() {
        return id;
    }

    public String transaction(String type, double sum) {
        if (type.equals("deposit")) {
            double amount = acc.deposit(sum);
            return "Zamestnanec " + id + ": vklad " + sum + "€ (zostatok " + amount + " €)";
        } else if (type.equals("withdraw")) {
            double amount = acc.withdraw(sum);
            if (amount == -1) {
                return "Zamestnanec " + id + ": výber " + sum + " € zamietnutý, nedostatok peňazí";
            }
             return "Zamestnanec " + id + ": výber " + sum + " € (zostatok " + amount + " €)";
        }
        else {
            return "";
        }
    }
}