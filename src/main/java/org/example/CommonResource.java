package org.example;

public class CommonResource {
    private final int[] buffer;

    private int inPtr;
    private int outPtr;
    private int count;

    public CommonResource(int bufferSize){
        this.buffer = new int[bufferSize];
        this.inPtr = 0;
        this.outPtr = 0;
        this.count = 0;
    }

    public synchronized void put(int data) {
        try {
            while(count > this.buffer.length){
                wait();
            }
            this.buffer[inPtr] = data;
            inPtr = (inPtr + 1) % buffer.length;
            count++;
            notifyAll();
            //System.out.println(Thread.currentThread().getName() + " puts " + data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized int get(){
        try {
            while (count <= 0) {
                wait();
            }
            int data = buffer[outPtr];
            outPtr = (outPtr + 1) % buffer.length;
            count--;
            notifyAll();
            //System.out.println(Thread.currentThread().getName() + " gets " + data);
            return data;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
