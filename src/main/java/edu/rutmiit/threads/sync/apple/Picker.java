package edu.rutmiit.threads.sync.apple;

class Picker implements Runnable {
    private final AppleBasket basket;
    private final String name;
    private int countApple;

    public Picker(AppleBasket basket, String name) {
        this.basket = basket;
        this.name = name;
    }

    @Override
    public void run() {
        while (basket.pickApple()) {
            try {
                countApple++;
                Thread.sleep(10); // Имитация времени на сбор яблока
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(name + " собрал " + countApple + " яблок");
    }
}