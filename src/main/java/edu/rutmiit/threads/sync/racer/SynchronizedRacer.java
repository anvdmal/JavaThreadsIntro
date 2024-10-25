package edu.rutmiit.threads.sync.racer;

import java.util.concurrent.CountDownLatch;

class SynchronizedRacer implements Runnable {
    private String name;
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;

    public SynchronizedRacer(String name, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.name = name;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await(); // Ждем сигнала старта
            System.out.println(name + " начал гонку");
            long startTime = System.nanoTime();

            int counter = 0;
            for (int i = 0; i < 1000000; i++) {
                counter++;
            }
            long endTime = System.nanoTime();
            long resultTime = (endTime - startTime) / 1000000;

            finishSignal.countDown(); // Сигнализируем о завершении
            System.out.println(name + ", итоговое время: " + resultTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

