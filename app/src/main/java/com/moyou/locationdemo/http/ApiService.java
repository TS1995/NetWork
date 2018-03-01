package com.moyou.locationdemo.http;

import com.alibaba.fastjson.JSONObject;
import com.moyou.locationdemo.bean.LikesBean;
import com.moyou.locationdemo.bean.StringBean;



import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public interface ApiService {

    @POST("user/add")
    Observable<JSONObject> register(@Body JSONObject jsonObject);

    //验证验证码
    @POST("sms/captcha/check")
    Observable<StringBean> sms_verificationCode_check(@Body JSONObject object);

    //点赞列表
    @POST("talk/queryPraiseList")
    Observable<LikesBean> getLikesDate(@Body JSONObject object);

}
