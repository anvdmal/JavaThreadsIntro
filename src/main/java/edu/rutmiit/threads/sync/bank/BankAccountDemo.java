package edu.rutmiit.threads.sync.bank;

import java.util.Random;

class BankAccount {
    private int balance = 1000;

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("+" + amount + ", баланс = " + this.balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance - amount < 0) {
            System.out.println("Баланс не может быть меньше нуля");
            return;
        }
        balance -= amount;
        System.out.println("-" + amount + ", баланс = " + this.balance);
    }

    public int getBalance() {
        return balance;
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Random random = new Random();
        final int[] correctValue = {0};
        System.out.println(account.getBalance());

        Thread[] threads = new Thread[6];
        for (int i = 0; i < threads.length; i++) {
            int randomValue = random.nextInt(2);
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (randomValue == 0) {
                        account.deposit(100);
                        correctValue[0] += 100;
                    }
                    else {
                        account.withdraw(900);
                        correctValue[0] -= 900;
                    }
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
        System.out.println("\nФинальный баланс: " + account.getBalance());
        System.out.println("Ожидаемое значение: " + correctValue[0]);
    }
}
