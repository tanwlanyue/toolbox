package com.tanwlanyue.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印
 * @author zhanglei211 on 2021/9/30.
 */
public class Demo {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile boolean flag = false;

    static volatile int num=1;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Print(0));
        thread1.start();
        Thread thread2 = new Thread(new Print(1));
        thread2.start();
    }

    static class Print implements Runnable {

        int x;

        public Print(int x) {
            this.x = x;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (num<=100){
                    if (num%2==x) {
                        System.out.println(Thread.currentThread().getName() + "当前条件不满足等待");
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "num:"+num);
                    num++;
                    condition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}