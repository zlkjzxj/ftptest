package com.zlkj.ftptest.test;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Author sunny
 * @Date 2019-04-04 15:19
 */
public class Ttest {
    public static void test1() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Hello World"));
    }

    public static void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> "Hello World");
// some operations
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>());
    }

    public static void main(String[] args) {
        test2();
    }
}
