package com.tr.thread.thread;

import com.tr.thread.utils.Util;

/**
 * 作者：tangren on 2019/3/20
 * 包名：com.tr.thread.thread
 * 邮箱：996489865@qq.com
 * TODO:实现runnable接口
 */

public class DemoRunnable implements Runnable {
    @Override
    public void run() {
        Util.sleep(2000);
        System.out.println("DemoRunnable.run :" + Thread.currentThread().getName() + "任务完成");
    }
}
