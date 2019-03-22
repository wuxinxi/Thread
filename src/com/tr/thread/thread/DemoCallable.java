package com.tr.thread.thread;

import com.tr.thread.utils.Util;

import java.util.concurrent.Callable;

/**
 * 作者：tangren on 2019/3/20
 * 包名：com.tr.thread.thread
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class DemoCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Util.sleep(10000);
        return Thread.currentThread().getName() + "任务完成,完成时间:" + System.currentTimeMillis() / 1000;
    }
}
