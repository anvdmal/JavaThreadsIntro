package edu.rutmiit.threads.sync.bank;

import java.util.Random;

class SynchronizedBankAccount {
    private volatile int balance = 1000000;

    public synchronized void deposit(int amount) {
        getAndSetBalance(amount);
        System.out.println("+" + amount + ", баланс = " + this.balance);
    }

    public synchronized void withdraw(int amount) {
        getAndSetBalance(amount);
        System.out.println(amount + ", баланс = " + this.balance);
    }

    public synchronized int getAndSetBalance(int amount) {
        if (this.balance + amount < 0) {
            System.out.print("ОТКАЗ ");
            return balance;
        }
        return this.balance += amount;
    }
}


public class SynchronizedBankAccountDemo {
    public static void main(String[] args) {
        SynchronizedBankAccount syncAccount = new SynchronizedBankAccount();
        Random random = new Random();
        System.out.println("Начальный баланс: " + syncAccount.getAndSetBalance(0));

        Thread[] threads = new Thread[6];
        for (int i = 0; i < threads.length; i++) {
            int randomValue = random.nextInt(2);
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (randomValue == 0) syncAccount.deposit(100);
                    else syncAccount.withdraw(-100);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Финальный баланс: " + syncAccount.getAndSetBalance(0));
    }
}
