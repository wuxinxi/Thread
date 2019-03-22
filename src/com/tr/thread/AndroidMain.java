package com.tr.thread;

import java.text.SimpleDateFormat;

/**
 * 作者：tangren on 2019/3/20
 * 包名：com.tr.thread
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

class AndroidMain {
    public static void main(String[] args) {

        Long aLong = timeStringToLong(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), "2018-12-30 12:12:12")/1000;

        System.out.println("AndroidMain.main="+aLong);

        System.out.println("AndroidMain.main"+(0/100));

    }

    public static Long timeStringToLong(SimpleDateFormat format, String timeString) {
        try {
            return format.parse(timeString).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

}
