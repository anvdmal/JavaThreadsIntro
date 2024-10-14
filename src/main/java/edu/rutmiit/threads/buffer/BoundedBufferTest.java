package edu.rutmiit.threads.buffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        int threadsCount = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(threadsCount * 2);

        for (int i = 1; i <= threadsCount; i++) {
            new Thread(new PutThreads(buffer, startSignal, finishSignal)).start();
        }

        for (int i = 1; i <= threadsCount; i++) {
            new Thread(new TakeThreads(buffer, startSignal, finishSignal)).start();
        }

        startSignal.countDown();
        finishSignal.await();
    }
}
