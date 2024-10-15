package edu.rutmiit.threads.buffer;

import java.util.concurrent.CountDownLatch;

public class TakeThreads implements Runnable {
    BoundedBuffer<Integer> buffer;
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;

    public TakeThreads(BoundedBuffer<Integer> buffer, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.buffer = buffer;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            buffer.take();
            finishSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
