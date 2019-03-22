package com.wxx.demo.exception;

/**
 * 作者：tangren on 2019/3/21
 * 包名：com.wxx.demo.exception
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class MyException extends Exception {
    private static final long serialVersionUID = 0L;
    private String errorBody;

    public MyException() {
    }

    public MyException(String errorBody) {
        super(errorBody);
    }

    public MyException(String errorBody, Throwable cause) {
        super(errorBody, cause);
    }

    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }
}
