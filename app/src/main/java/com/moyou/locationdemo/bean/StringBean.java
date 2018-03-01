package com.moyou.locationdemo.bean;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class StringBean extends BaseBean {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StringBean{" +
                "data='" + data + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
