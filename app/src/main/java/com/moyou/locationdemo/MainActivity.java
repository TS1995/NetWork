package com.moyou.locationdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.alibaba.fastjson.JSONObject;
import com.moyou.locationdemo.bean.BaseBean;
import com.moyou.locationdemo.bean.LikesBean;
import com.moyou.locationdemo.bean.StringBean;
import com.moyou.locationdemo.data.RegisterData;
import com.moyou.locationdemo.utils.LogUtil;

import java.net.ProtocolException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --------------------------------------------  创建操作 --------------------------------------------------


//        Observable.just("one", "two", "three", "four", "five")
//                .subscribeOn(Schedulers.newThread())  //在子线程工作
//                .observeOn(AndroidSchedulers.mainThread()) //在UI线程工作
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//
//                    }
//                });
//
//
//        Observable.just(1, 2, 3, 4, 5)
//                .subscribeOn(Schedulers.newThread())  //在子线程工作
//                .observeOn(AndroidSchedulers.mainThread()) //在UI线程工作
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e("TAG" ,"----》1111111111 ---->"+integer);
//                    }
//                });
//
//
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//            }
//        }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e("TAG" ,"----》"+integer);
//            }
//        });
        // --->timer() 创建一个在给定的延时之后发射数据
//        Observable.timer(1000 , TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Log.e("TAG" , "这里是淹死一秒钟...");
//            }
//        });

        //--->interval() 创建一个按照给定的时间间隔发射从0开始的整数序列
//        Observable.interval(1,1,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Log.e("TAG" , "------->"+aLong);
//            }
//        });

        // ---》range() 创建一个发射指定范围的整数序列
//        Observable.range(1 ,5).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e("TAG" , "------->"+integer);
//            }
//        });

    //--------------------------------- 合并操作 -------------------------------------

        // ----》concat() 按顺序连接多个Observables;
//        Observable.concat(Observable.just("1","2","3","4") ,Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("5");
//            }
//        })).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.e("TAG" , s);
//            }
//        });

        //------> startWith()   在数据序列的开头增加一项数据
//        Observable.just("1","2","3",4).startWith(6).subscribe(new Consumer<Serializable>() {
//            @Override
//            public void accept(Serializable serializable) throws Exception {
//                Log.e("TAG" , serializable.toString());
//            }
//        });

        //----->merge ()  将多个Observable合并为一个。不同于concat，merge不是按照添加顺序连接，而是按照时间线来连接
//        Observable.merge(Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("5");
//            }
//        }),Observable.just("1","2","3","4")).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.e("TAG" , s);
//            }
//        });

        //----》zip() 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果。如果多个Observable发射的数据量不一样，则以最少的Observable为标准进行压合。内部通过OperatorZip进行压合。
//        Observable<Integer>  observable1=Observable.just(1,2,3,4);
//        Observable<Integer>  observable2=Observable.just(4,5,6);
//        Observable<Integer>  observable3=Observable.just(7,8,9);
//        Observable.zip(observable1, observable2, observable3, new Function3<Integer, Integer, Integer, String>() {
//            @Override
//            public String apply(Integer integer, Integer integer2, Integer integer3) throws Exception {
//                return integer + "--->" + integer2 + "---->" + integer3;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.e("TAG" , s);
//            }
//        });



        //-----------------------------------------------  过滤操作   ----------------------------------------

        //filter() 过滤数据。内部通过OnSubscribeFilter过滤数据。
//        Observable.just("1","2","3","4").filter(new Predicate<String>() {
//            @Override
//            public boolean test(String s) throws Exception {
//                return s.equals("3");
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.e("TAG" , s);
//            }
//        });


        //ofType： 过滤指定类型的数据，与filter类似
//        Observable.just("1","2","3",4).ofType(Integer.class).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e("TAG"  , "---->"+integer);
//            }
//        });


        //----》take() 只发射开始的N项数据或者一定时间内的数据。内部通过OperatorTake和OperatorTakeTimed过滤数据。
//        Observable.just("1","2","3",4).take(2).take(1000 ,TimeUnit.MILLISECONDS).subscribe(new Consumer<Serializable>() {
//            @Override
//            public void accept(Serializable serializable) throws Exception {
//                Log.e("TAG"  , "---->"+serializable.toString());
//            }
//        });

        //first() /firstOrDefault()：只发射第一项（或者满足某个条件的第一项）数据，可以指定默认值。
//        Observable.just("1","2","3",4).first(4 ).subscribe(new Consumer<Serializable>() {
//            @Override
//            public void accept(Serializable serializable) throws Exception {
//                Log.e("TAG" , serializable.toString());
//            }
//        });


//        Observable.just(list).flatMap(new Function<ArrayList<String>, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<String> apply(ArrayList<String> strings) throws Exception {
//                return Observable.fromIterable(strings);
//            }
//        }).take(2).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object s) throws Exception {
//                Log.e("TAG" ,(String)s);
//            }
//        });

//        Observable.just(list).flatMap(new Function<ArrayList<Person>, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(ArrayList<Person> strings) throws Exception {
//                return Observable.fromIterable(strings);
//            }
//        }).doOnNext(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                Log.e("TAG" , "开始工作l------->"+o.toString());
//            }
//        }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                Log.e("TAG" ,(String)o);
//            }
//        });

//        ArrayList<Person> list = new ArrayList<>();
//       Person person = new Person(10,"ts");
//        list.add(person);
//        list.add(new Person(12,"ms"));
//        list.add(new Person(55,"as"));
//        list.add(new Person(22,"rs"));
//        Observable.fromArray(list).flatMap(new Function<ArrayList<Person>, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(ArrayList<Person> people) throws Exception {
//                return Observable.fromIterable(people);
//            }
//        }).all(new Predicate<Object>() {
//            @Override
//            public boolean test(Object o) throws Exception {
//                return ((Person)o).getAge() > 30;
//            }
//        }).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                Log.e("TAG" , "----->"+aBoolean);
//            }
//        });

//        Observable.fromIterable(list).contains(person).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean person) throws Exception {
//                Log.e("TAG" , person.toString());
//            }
//        });

//        Observable.fromIterable(list).map(new Function<Person, Integer>() {
//            @Override
//            public Integer apply(Person person) throws Exception {
//                return person.getAge();
//            }
//        }).reduce(new BiFunction<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) throws Exception {
//                return integer+ integer2;
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//
//            }
//        });
//        compositeDisposable = new CompositeDisposable();
//
//        RxBus.getInstance().toObservable(Person.class).subscribe(new Observer<Person>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                compositeDisposable.add(d);
//            }
//
//            @Override
//            public void onNext(Person person) {
//                Log.e("TAG"  ,person.toString());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });







//        Observable.fromIterable(list).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//
//            }
//        });

        JSONObject jsonObject =new JSONObject();
        jsonObject.put("pageNumber" , 1);
        jsonObject.put("pageSize" , 10000);
        jsonObject.put("userId",10866);
        //进行网络请求
        RegisterData registerData = new RegisterData();
        registerData.onCreate(jsonObject);
//        registerData.unSubscribe();
    }

    public void sendBus(View view){
        RxBus.getInstance().post( new Person(0 ,"jjj"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.rxBusUnbund(compositeDisposable);

    }
}
