package com.tanwlanyue.threadpool;

/**
 * @author zhanglei211 on 2021/9/22.
 */
public class JobDemo implements Runnable {

    private int num;

    public JobDemo(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        if (num % 5 == 0) {
            throw new RuntimeException("mod5");
        } else {
            System.out.println("num:" + num);
        }
    }
}
