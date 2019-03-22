package com.tr.thread;

import com.tr.thread.pool.ThreadPoosManager;
import com.tr.thread.utils.Util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 作者：tangren on 2019/3/20
 * 包名：com.tr.thread
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class Main {

    static long start;
    static long end;
    static long main;

    static int count = 0;
    static CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {
//        List<FutureTask<String>> list = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            DemoCallable callable = new DemoCallable();
//            FutureTask<String> futureTask = new FutureTask<>(callable);
//            list.add(futureTask);
//
//
//        }
//
//        StringBuilder results = new StringBuilder();
//        list.forEach(stringFutureTask ->
//        {
//            try {
//                results.append(stringFutureTask.get()).append("\n");
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        });
//
//        System.out.println("Main.main：" + results.toString());
//
//        System.out.println("Main.main");


//        ThreadPoosManager.getInstance().execute(new MyThread());
//        ThreadPoosManager.getInstance().execute(new MyThread());
//        ThreadPoosManager.getInstance().execute(new MyThread());
//        ThreadPoosManager.getInstance().execute(new MyThread());
//

        ThreadPoosManager.getInstance().exeCycleTask("tag", new MyThread(), 3, 1, TimeUnit.SECONDS);

//        Thread.sleep(50* 1000);
//        ThreadPoosManager.getInstance().showDownAll();

    }

    static class MyThread extends Thread {
        private int count = 0;

        @Override
        public void run() {
            super.run();
            System.out.println(Thread.currentThread().getName() + "任务开始" + Thread.currentThread().hashCode()+",count="+count++);
            Util.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "任务结束");
        }
    }

    public static synchronized void add() {
        count++;
    }
}
