package com.tanwlanyue.singleton;

/**
 * @author zhanglei211 on 2021/10/25.
 */
public class Singleton2 {

    private static Singleton2 INSTANCE;

    public synchronized static Singleton2 getInstance() {
        if(INSTANCE==null){
            INSTANCE=new Singleton2();
        }
        return INSTANCE;
    }
}
