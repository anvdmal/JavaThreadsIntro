package edu.rutmiit.threads.buffer;

import java.util.Arrays;

public class BoundedBuffer<T> {
    private final T[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;

    @SuppressWarnings("unchecked")
    public BoundedBuffer(int size) {
        buffer = (T[]) new Object[size];
    }

    public synchronized void put(T item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[in] = item;
        count++;
        in++;
        out++;
        System.out.println(Arrays.toString(buffer));
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        out = count - 1;
        T item = buffer[out];
        buffer[out] = null;
        count--;
        out--;
        in--;
        System.out.println(Arrays.toString(buffer) + ", удален " + item);
        notifyAll();
        return item;
    }
}
