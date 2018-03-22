package com.example.administrator.mvpsample.utils;

import com.example.administrator.mvpsample.bean.HistoryResponseBean;
import com.example.administrator.mvpsample.bean.RequestBean;
import com.example.administrator.mvpsample.bean.ResultBean;
import com.example.baselibrary.http.*;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface MyService {
/*
    //get 请求 @Query传参数
    @GET("user/login")
    public abstract Observable<Object> login(@Query("username") String username, @Query("password") String password);
    //post 请求 通过 @Body 传实体
    @POST("login")
    public abstract Observable<ResultBean> login(@Body RequestBean bean);

 */
    //http://route.showapi.com/
    @GET("119-42")
    public abstract Observable<HistoryResponseBean>getHistory(@Query("showapi_appid") String showapi_appid,@Query("showapi_sign") String showapi_sign,@Query("date") String date);

}
