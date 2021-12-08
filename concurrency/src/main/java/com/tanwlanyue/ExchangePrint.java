package com.tanwlanyue;

/**
 * @author zhanglei211 on 2021/9/30.
 */
public class ExchangePrint {

    static volatile int num=1;

    static Object monitor=new Object();

    public static void main(String[] args) {
        new Thread(new Print(0)).start();
        new Thread(new Print(1)).start();
    }

    static class Print implements Runnable{
        int x;

        public Print(int x) {
            this.x = x;
        }

        @Override
        public void run() {
            synchronized (monitor){
                while (num<=100){
                    if(num%2==x){
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(num+" "+Thread.currentThread().getName());
                    num++;
                    monitor.notify();
                }
            }
        }
    }
}
