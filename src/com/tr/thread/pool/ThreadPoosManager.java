package com.tr.thread.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者：tangren on 2019/3/22
 * 包名：com.tr.thread.pool
 * 邮箱：996489865@qq.com
 * TODO:线程池管理类（周期任务+普通任务）
 */

public class ThreadPoosManager {
    //核心线程数
    private static final int coreSize = Runtime.getRuntime().availableProcessors() * 2;
    //最大线程数
    private static final int maxSize = 20;
    //存活时间
    private static final long keepAliveTime = 1L;
    //普通任务线程
    private ThreadPoolExecutor executor;
    //定时任务线程
    private ScheduledExecutorService executorService;
    private Map<String, Future> futureMap = new HashMap<>();
    private AtomicInteger runnableCount = new AtomicInteger(0);
    private volatile static ThreadPoosManager instance = null;

    private ThreadPoosManager() {
        //队列大小为核心线程的3倍
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(30);
        ThreadFactory factory = r -> {
            Thread thread = new Thread(r);
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setName(">_" + runnableCount.getAndIncrement());
            return thread;
        };
        executor = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.SECONDS, queue, factory, new ThreadPoolExecutor.DiscardOldestPolicy());
        executorService = new ScheduledThreadPoolExecutor(coreSize, factory, new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static ThreadPoosManager getInstance() {
        if (instance == null) {
            synchronized (ThreadPoosManager.class) {
                if (instance == null) {
                    instance = new ThreadPoosManager();
                }
            }
        }
        return instance;
    }

    /**
     * 提交普通任务
     *
     * @param runnable 任务
     */
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * 提交有返回的普通任务
     *
     * @param runnable 任务
     * @return .
     */
    public Future<?> submit(Runnable runnable) {
        return executor.submit(runnable);
    }

    /**
     * 执行延时任务
     *
     * @param command 任务
     * @param delay   延时时间
     * @param unit    时间单位
     * @return .
     */
    public ScheduledFuture<?> exeDelayTask(Runnable command,
                                           long delay, TimeUnit unit) {
        return executorService.schedule(command, delay, unit);
    }

    /**
     * @param tag          任务标志
     * @param command      任务
     * @param initialDelay 延迟执行的时间
     * @param period       循环周期
     * @param unit         时间单位
     */
    public void exeCycleTask(String tag, Runnable command,
                             long initialDelay,
                             long period,
                             TimeUnit unit) {
        if (futureMap.get(tag) == null) {
            executorService.scheduleAtFixedRate(command, initialDelay, period, unit);
        }
    }


    /**
     * 停止循环任务
     *
     * @param tag .
     */
    public void stopTask(String tag) {
        Future future = futureMap.get(tag);
        if (future != null && !future.isCancelled()) {
            future.cancel(true);
            futureMap.remove(future);
        }
    }

    /**
     * 关闭周期任务线程池
     */
    public void shutDownCycle() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    /**
     * 关闭普通任务线程池
     */
    public void shutDownNormal() {
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }

    /**
     * 关闭所有任务
     */
    public void showDownAll() {
        shutDownCycle();
        shutDownNormal();
    }

}
