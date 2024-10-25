package edu.rutmiit.threads.notSync;

public class RacerDemo {
    public static void main(String[] args) {
// TODO: Создайте и запустите несколько "гонщиков"
        Thread racer1 = new Thread(new Racer("Гонщик 1"));
        Thread racer2 = new Thread(new Racer("Гонщик 2"));
        Thread racer3 = new Thread(new Racer("Гонщик 3"));
        Thread racer4 = new Thread(new Racer("Гонщик 4"));
        Thread racer5 = new Thread(new Racer("Гонщик 5"));
        Thread racer6 = new Thread(new Racer("Гонщик 6"));
        Thread racer7 = new Thread(new Racer("Гонщик 7"));

        racer1.start();
        racer2.start();
        racer3.start();
        racer4.start();
        racer5.start();
        racer6.start();
        racer7.start();

// TODO: Дождитесь завершения всех "гонщиков"
        try {
            racer1.join();
            racer2.join();
            racer3.join();
            racer4.join();
            racer5.join();
            racer6.join();
            racer7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Racer implements Runnable {
    private String name;

    public Racer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        // TODO: Реализуйте логику "гонки"
        int counter = 0;
        for (int i = 0; i < 1000000; i++) {
            counter++;
        }
        long endTime = System.nanoTime();
        long resultTime = (endTime - startTime) / 1000000;
        // TODO: Выведите в консоль информацию о финише и времени гонки
        System.out.println("ФИНИШ! " + name + "! Итоговое время: " + resultTime);
    }
}

