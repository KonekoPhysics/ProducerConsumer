package org.example;

public class Main {
    public static void main(String[] args) {
        //回数を30回とする
        int n = 30;
        //共有リソースを初期化
        CommonResource commonResource = new CommonResource(4);

        //スレッドのスタート
        new Producer("Producer-1", commonResource, 31415, n).start();
        new Producer("Producer-2", commonResource, 92653, n).start();
        new Consumer("Consumer-1", commonResource, 32384, n).start();
        new Consumer("Consumer-2", commonResource, 62643, n).start();
    }
}