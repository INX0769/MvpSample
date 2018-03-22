package com.example.baselibrary.http;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpHelper {
    private static String DEFAULT_URL = "";
    private static Map<String, Retrofit> srMapping;
    private static HttpHelper instance;
    private static Retrofit currentRetrofit;

    private HttpHelper() {

    }

    public static HttpHelper instance() {
        return instance == null ? new HttpHelper() : instance;
    }

    public HttpHelper baseUrl() {
        return baseUrl(null);
    }

    public HttpHelper baseUrl(String baseUrl) {
        if (srMapping == null)
            srMapping = new HashMap<>();
        baseUrl = checkNull(baseUrl, DEFAULT_URL);
        if (srMapping.get(baseUrl) != null) {
            currentRetrofit = srMapping.get(baseUrl);
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                    .baseUrl(baseUrl)
                    .build();
            srMapping.put(baseUrl, retrofit);
            currentRetrofit = retrofit;
        }
        return this;
    }

    public <Service> Service createService(Class<Service> serviceClass) {
        if (currentRetrofit == null)
            baseUrl();
        return currentRetrofit.create(serviceClass);
    }

    private static Observable.Transformer transformer;

    public static <T> Observable<T>  transform(Observable<T> mObservable) {
        if (mObservable == null)
            return mObservable;
        if (transformer == null) {
            transformer = new Observable.Transformer() {
                @Override
                public Object call(Object o) {
                    return ((Observable) o).subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
        return mObservable.compose(transformer);
    }
    public static String checkNull(String str, String space) {
        return str == null ? space : str;
    }

    public static String checkNull(String str) {
        return checkNull(str, "");
    }
}
