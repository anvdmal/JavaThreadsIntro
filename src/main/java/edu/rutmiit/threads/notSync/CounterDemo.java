package edu.rutmiit.threads.notSync;

public class CounterDemo {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        final int INCREMENTS_PER_THREAD = 100000;
        Counter counter = new Counter();
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int c = 0; c < INCREMENTS_PER_THREAD; c++) {
                        counter.increment();
                    }
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
// TODO: Создайте и запустите потоки, увеличивающие счетчик
// TODO: Дождитесь завершения всех потоков
        System.out.println("Ожидаемое значение: " + (NUM_THREADS * INCREMENTS_PER_THREAD));
        System.out.println("Фактическое значение: " + counter.getCount());
    }
}

class Counter {
    private int count = 0;
    public void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}