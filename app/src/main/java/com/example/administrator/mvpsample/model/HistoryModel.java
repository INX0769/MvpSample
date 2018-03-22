package com.example.administrator.mvpsample.model;

import android.util.Log;

import com.example.administrator.mvpsample.bean.HistoryBean;
import com.example.administrator.mvpsample.bean.HistoryResponseBean;
import com.example.administrator.mvpsample.bean.RequestBean;
import com.example.administrator.mvpsample.utils.MyService;
import com.example.baselibrary.http.HttpHelper;
import com.example.baselibrary.mvp.BaseModel;

import java.util.List;

import rx.Subscriber;

public class HistoryModel implements BaseModel {

    public void getData(String date, final DataCallBack<List<HistoryBean>> callBack) {
        if (callBack==null)
            return;
        MyService service = HttpHelper.instance().baseUrl("http://route.showapi.com/").createService(MyService.class);
        HttpHelper.transform(service.getHistory("59765","985023e0d3e04539b0c7f7c9cc248473",date)).subscribe(new Subscriber<HistoryResponseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("httphelper", "onError: "+e.toString());
                callBack.onError("获取数据失败！");
            }

            @Override
            public void onNext(HistoryResponseBean historyResponseBean) {
                Log.i("httphelper", "resp:: "+historyResponseBean);
                if (historyResponseBean.getShowapi_res_code()==0) {
                    callBack.onSuccess(historyResponseBean.getShowapi_res_body().getList());
                }else{
                    onError(null);
                }
            }
        });
    }

}
