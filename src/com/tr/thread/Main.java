package com.tr.thread;

import com.tr.thread.utils.Util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        //使用线程管理工具
//        ThreadPoosManager.getInstance().execute(new MyThread());

        /*
         newFixedThreadPool
         1.核心数等于最大线程数
         2.存活时间为0,无意义,因为无其他线程
         3.阻塞队列使用了LinkedBlockingQueue,大小无限
         3.执行完的任务会反复从任务队列去取任务
         常用于负载比较重的服务器，为了资源合理利用，需要限制当前的线程数
         */
        ExecutorService executors = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
//            executors.execute(new MyThread());
        }

        /*
         *newCachedThreadPool
         * 1.核心线程为0，全是外包团队
         * 2.最大线程数无限
         * 3.线程存活时间60S
         * 4.阻塞队列为SynchronousQueue
         *流程：直接向SynchronousQueue提交任务，如果有空闲线程就去取任务如果没有就新创建，执行完任务的线程有60s的存活时间
         *     在此期间可以接受任务，超过存活时间就被回收
         * 如果当前提交任务的速度大于执行的速度极端情况下将会创建大量线程耗尽CPU和内存资源
         * 由于有60S的存活时间，长时间保持空闲并不会占用任何资源
         * 用于并发执行大量短暂的小任务，或是负载比较轻的服务器
         */
        ExecutorService cachedExecutors = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedExecutors.execute(new MyThread());
        }



    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            System.out.println(Thread.currentThread().getName() + "任务开始" + Thread.currentThread().hashCode() + ",hashCode=" + this.hashCode());
            Util.sleep(5000);
//            System.out.println(Thread.currentThread().getName() + "任务结束");
        }
    }

    public static synchronized void add() {
        count++;
    }
}
