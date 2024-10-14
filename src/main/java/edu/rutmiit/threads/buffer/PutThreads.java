package edu.rutmiit.threads.buffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class PutThreads implements Runnable {
    private Random random = new Random();
    private BoundedBuffer<Integer> buffer;
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;

    public PutThreads(BoundedBuffer<Integer> buffer, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.buffer = buffer;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            buffer.put(random.nextInt(1, 10));
            finishSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
