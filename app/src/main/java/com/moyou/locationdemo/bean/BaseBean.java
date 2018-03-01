package com.moyou.locationdemo.bean;

import java.io.Serializable;

/**
 * Created by Administrator
 * on 2017/3/6 0006.
 */

public class BaseBean implements Serializable {
    public int code;
    public String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
