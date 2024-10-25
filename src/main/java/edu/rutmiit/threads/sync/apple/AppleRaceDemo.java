package edu.rutmiit.threads.sync.apple;

class AppleRaceDemo {
    public static void main(String[] args) {
        AppleBasket basket = new AppleBasket();
        Thread picker1 = new Thread(new Picker(basket, "Сборщик 1"));
        Thread picker2 = new Thread(new Picker(basket, "Сборщик 2"));
        picker1.start();
        picker2.start();
        try {
            picker1.join();
            picker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Гонка завершена! Всего собрано яблок: " + basket.apples);
    }
}