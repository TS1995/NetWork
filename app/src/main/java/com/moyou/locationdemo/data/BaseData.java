package com.moyou.locationdemo.data;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.moyou.locationdemo.bean.BaseBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public abstract  class BaseData<T extends BaseBean> {

    private CompositeDisposable compositeSubscription ;

    public BaseData() {
        compositeSubscription = new CompositeDisposable();;
    }

    protected abstract Observable<T> getObservable(JSONObject jsonObject);

    protected abstract void  onNetworkRequestSuccess(T date);

    protected abstract void  onNetworkRequestError(String error);


    public void onCreate(JSONObject jsonObject ){
        getObservable(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<T>() {
                    @Override
                    public boolean test(T t) throws Exception {
                        if (t.getCode() == 0){
                            //code为0时是错误返回就不需要往下返回
                            onNetworkRequestError("数据异常!请稍后再试.....");
                            return false;
                        }
                        return true;
                    }
                }).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeSubscription.add(d);
            }

            @Override
            public void onNext(T data) {
                onNetworkRequestSuccess(data);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG" , e.getMessage());
                onNetworkRequestError("网络连接异常.....");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    //当页面销毁时释放数据
    public void unSubscribe(){
        if (compositeSubscription!= null) {
            if (compositeSubscription.isDisposed())
                 compositeSubscription.clear();
        }
    }

}
