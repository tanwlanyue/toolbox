package com.tanwlanyue.singleton;

/**
 * @author zhanglei211 on 2021/10/25.
 */
public class Singleton3 {

    private volatile static Singleton3 INSTANCE;

    public static Singleton3 getInstance() {
        if(INSTANCE==null){
            synchronized (Singleton3.class){
                if(INSTANCE==null){
                    INSTANCE=new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
