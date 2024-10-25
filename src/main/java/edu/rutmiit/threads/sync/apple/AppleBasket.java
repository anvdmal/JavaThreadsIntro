package edu.rutmiit.threads.sync.apple;

class AppleBasket {
    public int apples = 0;
    private final int maxApples = 21;

    // Синхронизированный метод для добавления яблок в корзину
    public synchronized boolean pickApple() {
        if (apples < maxApples) {
            apples++;
            return true;
        }
        return false;
    }
}

