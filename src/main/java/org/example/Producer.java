package org.example;

import java.util.Random;

public class Producer extends Thread{

    //sleepのための乱数シード
    private final Random random;

    //共有リソース
    private final CommonResource commonResource;

    //回数
    private final int n;

    //コンストラクタ
    public Producer(String name, CommonResource commonResource, long seed, int n) {
        //名前，共有リソース，乱数シード，回数の初期値を設定
        super(name);
        this.commonResource = commonResource;
        this.random = new Random(seed);
        this.n = n;
    }

    //run関数のオーバーライド
    @Override
    public void run() {
        //30回繰り返す
        for(int i = 0; i < n; i++){
            try {
                //0〜100ミリ秒の間で適当な秒数だけsleep
                Thread.sleep(random.nextInt(100));
                //共有リソースに1をおく
                commonResource.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
