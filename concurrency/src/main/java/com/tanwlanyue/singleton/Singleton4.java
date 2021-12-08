package com.tanwlanyue.singleton;

/**
 * @author zhanglei211 on 2021/10/25.
 */
public class Singleton4 {

    private Singleton4() {
    }

    private static class Singleton4Holder{
        public static Singleton4 INSTANCE=new Singleton4();
    }

    public static Singleton4 getInstance() {
        return Singleton4Holder.INSTANCE;
    }
}
