package com.example.banka;

public class BankAccount {
    private double sum;
    public BankAccount(double s) {
        if (s < 0.0)
            throw new RuntimeException("zaporna suma");

        sum = s;
    }
    public synchronized double getAmount() {
        return sum;
    }
    public synchronized double deposit(double s) {
        sum += s;
        return sum;
    }
    public synchronized double withdraw(double s) {
        if (s <= 0.0) {
            throw new RuntimeException("zaporna suma");
        }
        if (s > sum)
            return -1;
        sum -= s;

        return sum;
    }
}
