package org.example;

public class Main {
    public static void main(String[] args) {
        int n = 30;
        CommonResource commonResource = new CommonResource(4);
        new Producer("Producer-1", commonResource, 31415, n).start();
        new Producer("Producer-2", commonResource, 92653, n).start();
        new Consumer("Consumer-1", commonResource, 32384, n).start();
        new Consumer("Consumer-2", commonResource, 62643, n).start();
    }
}