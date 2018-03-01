package com.moyou.locationdemo.data;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.moyou.locationdemo.HttpApplition;
import com.moyou.locationdemo.bean.BaseBean;
import com.moyou.locationdemo.bean.LikesBean;

import org.json.JSONArray;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class RegisterData extends BaseData<LikesBean> {


    @Override
    public Observable<LikesBean> getObservable(JSONObject jsonObject) {
        return HttpApplition.getApiService().getLikesDate(jsonObject);
    }

    @Override
    protected void onNetworkRequestSuccess(LikesBean date) {
        Log.e("TAG" , date.toString());
    }

    @Override
    protected void onNetworkRequestError(String error) {
        Log.e("TAG" , error);
    }

}
