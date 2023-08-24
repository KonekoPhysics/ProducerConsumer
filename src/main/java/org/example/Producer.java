package org.example;

import java.util.Random;

public class Producer extends Thread{
    private final Random random;
    private final CommonResource commonResource;

    private final int n;


    public Producer(String name, CommonResource commonResource, long seed, int n) {
        super(name);
        this.commonResource = commonResource;
        this.random = new Random(seed);
        this.n = n;
    }

    public void run() {
        for(int i = 0; i < n; i++){
            try {
                Thread.sleep(random.nextInt(100));
                commonResource.put(1);
            } catch (InterruptedException e) {
            }
        }
    }

}
