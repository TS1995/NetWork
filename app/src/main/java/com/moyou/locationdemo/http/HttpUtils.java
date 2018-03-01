package com.moyou.locationdemo.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.moyou.locationdemo.HttpApplition;
import com.moyou.locationdemo.utils.LogUtil;
import com.moyou.locationdemo.utils.MD5;
import com.moyou.locationdemo.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class HttpUtils {

    private static final  int NET_MAX = 30;  //链接超市的最长时间

    private static final int NET_NO_MAX = 60 * 60 * 24 * 7 ; //无网超时时间

    private OkHttpClient okHttpClient  = null; //网络请求的核心库

    private Retrofit retrofit = null;

    private HttpLoggingInterceptor httpLoggingInterceptor ;

    private  Interceptor mNetInterceptor; //网络状态的拦截器

    //生成单利模式
    private static  HttpUtils httpUtils;

    private   Cache cache ; //设置网络请求失败的缓存大小

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static HttpUtils getHttpUtils(){
        return httpUtils == null ? httpUtils = new HttpUtils() : httpUtils;
    }

    //在构造方法中初始化
    private HttpUtils() {
        initInterceptor(); // 初始化拦截器
        initCacheFile();
        initOkHttpClient(); //
        initRetrofit();
    }

    //初始化缓存大小
    private void initCacheFile() {
        File mFile = new File(HttpApplition.getInstance().getCacheDir() + "http");//储存目录
        long max_size = 10 * 1024 *1024;
        cache = new Cache( mFile , max_size );
    }

    private void initInterceptor() {
        //log拦截器
        httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.e("TAG" ,message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //网络状态的监听
         mNetInterceptor = new Interceptor() {
            @SuppressLint("MissingPermission")
            @Override
            public Response intercept(Chain chain) throws IOException {
               Request request =  chain.request();
               String timestamp =  String.valueOf(System.currentTimeMillis());
                request = request.newBuilder()
                        .addHeader("timestamp" , timestamp)
                        .addHeader("v" ,"1_4.0.0" )
                        .addHeader("key" , MD5.getMD5Code("010E1A172997D074AEABA77A908DBA2B" + timestamp))
                        .addHeader("deviceNumber" , ((TelephonyManager) HttpApplition.getInstance().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId())
                        .build();
                return chain.proceed(request);
            }
        };


    }


    //初始化网络链接器
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(mNetInterceptor) //网络拦截器
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    //初始化Retrofit
    private void initRetrofit(){
         retrofit =new  Retrofit.Builder()
                .baseUrl(BaseUrl.BASE_URL)  //设置baseurl
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .client(okHttpClient) //设置网络连接器
                .build();
    }
}
