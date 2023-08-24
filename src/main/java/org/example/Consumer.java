package org.example;

import java.util.Random;

public class Consumer extends Thread{
    private final Random random;
    private final CommonResource commonResource;

    private final int n;
    private int sum;

    public Consumer(String name, CommonResource commonResource, long seed, int n) {
        super(name);
        this.commonResource = commonResource;
        this.random = new Random(seed);
        this.n = n;
        this.sum = 0;
    }

    public void run() {
        for(int i = 0; i < n; i++) {
            try {
                int data = commonResource.get();
                sum += data;
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
            }
        }
        System.out.printf(getName() + ": sum = %d\n", sum);
    }
}
