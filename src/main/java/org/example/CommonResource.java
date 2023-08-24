package org.example;

public class CommonResource {

    //バッファ
    private final int[] buffer;
    //putされる位置
    private int inPtr;
    //getされる位置
    private int outPtr;
    //put数とget数の差（これがバッファ長より大きくなったり，0以下になったりしてはいけない）
    private int count;

    //コンストラクタ
    public CommonResource(int bufferSize){
        //クラス変数たちを初期化
        this.buffer = new int[bufferSize];
        this.inPtr = 0;
        this.outPtr = 0;
        this.count = 0;
    }

    //put関数（値をおく）．同期関数として定義
    public synchronized void put(int data) {
        try {
            //条件変数的．countがバッファ長以上の時は生成してはならない
            while(count >= this.buffer.length){
                wait();
            }
            //値を追加
            this.buffer[inPtr] = data;
            //inPtrを更新
            inPtr = (inPtr + 1) % buffer.length;
            //count変数に1を足す
            count++;
            //cond_postみたいなやつ．waitセットにあるすべてのスレッドを再開
            notifyAll();
            //System.out.println(Thread.currentThread().getName() + " puts " + data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //gett関数（値をとる）．同期関数として定義
    public synchronized int get(){
        try {
            //条件変数的．countが0以下の時は取り出してはならない
            while (count <= 0) {
                wait();
            }
            //データを取り出し
            int data = buffer[outPtr];
            //outPtrを更新
            outPtr = (outPtr + 1) % buffer.length;
            //countから1を引く
            count--;
            //cond_postみたいなやつ．waitセットにあるすべてのスレッドを再開
            notifyAll();
            //System.out.println(Thread.currentThread().getName() + " gets " + data);
            return data;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
