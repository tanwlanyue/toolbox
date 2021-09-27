package com.tanwlanyue.keyword;

/**
 * @author zhanglei211 on 2021/9/22.
 */
public class VolatileDemo {
    static boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("flag=" + flag);
        }).start();

        while (true) {
            if (flag) {
                System.out.println("flag==true");
            }
        }
    }
}
