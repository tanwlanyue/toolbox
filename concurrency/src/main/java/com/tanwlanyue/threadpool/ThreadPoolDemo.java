package com.tanwlanyue.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author zhanglei211 on 2021/9/22.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> System.out.println("exception!!!")).build();
        ExecutorService threadPool =
                new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), threadFactory);
        for (int i = 1; i <= 50; i++) {
            // submit提交的任务，如果异常了不会抛出异常信息 所以要setUncaughtExceptionHandler
            threadPool.submit(new JobDemo(i));
            // threadPool.execute(()->{}); 异常了外层能捕获到
        }
        threadPool.shutdown();
    }
}
