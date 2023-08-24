package org.example;

import java.util.Random;

public class Consumer extends Thread{

    //sleepのための乱数シード
    private final Random random;

    //共有リソース
    private final CommonResource commonResource;

    //回数
    private final int n;

    //総和
    private int sum;

    //コンストラクタ
    public Consumer(String name, CommonResource commonResource, long seed, int n) {
        //名前，共有リソース，乱数シード，回数の初期値を設定．sumには0を代入
        super(name);
        this.commonResource = commonResource;
        this.random = new Random(seed);
        this.n = n;
        this.sum = 0;
    }

    //run関数のオーバーライド
    @Override
    public void run() {
        //30回繰り返す
        for(int i = 0; i < n; i++) {
            try {
                //共有リソースから値を取ってくる
                int data = commonResource.get();
                // sumに足す
                sum += data;
                //0〜100ミリ秒の間で適当な秒数だけsleep
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //sumの値を表示．その際，スレッド名も表示．
        System.out.printf(getName() + ": sum = %d\n", sum);
    }
}
