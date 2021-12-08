package com.tanwlanyue.singleton;

/**
 * @author zhanglei211 on 2021/10/25.
 */
public class Singleton {

    private static Singleton INSTANCE = new Singleton();

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
