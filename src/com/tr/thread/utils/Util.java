package com.tr.thread.utils;

/**
 * 作者：tangren on 2019/3/20
 * 包名：com.tr.thread.utils
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class Util {

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
