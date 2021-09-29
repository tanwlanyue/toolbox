package com.tanwlanyue.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhanglei211 on 2021/9/29.
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {

//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            LockSupport.park();
//            System.out.println("1");
//            LockSupport.park();
//            System.out.println("2");
//        });
//        thread.start();
//        LockSupport.unpark(thread);
//        LockSupport.unpark(thread);
//        System.out.println("unpark");

        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println("1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println("2");
        });
        thread.start();
        Thread.sleep(3000);
        LockSupport.unpark(thread);
        Thread.sleep(6000);
        LockSupport.unpark(thread);
    }
}
