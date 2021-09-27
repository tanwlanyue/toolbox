package com.tanwlanyue.keyword;

/**
 * @author zhanglei211 on 2021/9/22.
 */
public class Singleton {
    // 1.分配内存 2.初始化 3.引用指向内存

    private static volatile Singleton instance =null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private Singleton() {
    }
}
