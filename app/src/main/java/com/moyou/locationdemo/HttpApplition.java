package com.moyou.locationdemo;

import android.app.Application;

import com.moyou.locationdemo.http.ApiService;
import com.moyou.locationdemo.http.HttpUtils;
import com.moyou.locationdemo.utils.LogUtil;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class HttpApplition extends Application {
    private static HttpApplition instance;

    public static HttpApplition getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtil.IS_DEBUG = true;
    }


    public static  ApiService getApiService(){
        return HttpUtils.getHttpUtils().getRetrofit().create(ApiService.class);
    }

}
