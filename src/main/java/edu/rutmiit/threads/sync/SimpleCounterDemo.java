package edu.rutmiit.threads.sync;

public class SimpleCounterDemo {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        final int INCREMENTS_PER_THREAD = 300000;
        SimpleCounter counter = new SimpleCounter();
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

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int c = 0; c < INCREMENTS_PER_THREAD; c++) {
                        counter.decrement();
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
//        System.out.println("Ожидаемое значение: " + (NUM_THREADS * INCREMENTS_PER_THREAD));
        System.out.println("Фактическое значение: " + counter.getCount());
    }
}

class SimpleCounter {
    private int count = 0;
    public synchronized void increment() {
//        if (count == 1000000) return;
        count++;
    }
    public synchronized void decrement() {
        if (count == 10) return;
        count--;
    }
    public synchronized int getCount() {
        return count;
    }
}

