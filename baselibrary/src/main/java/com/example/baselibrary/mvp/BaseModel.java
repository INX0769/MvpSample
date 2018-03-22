package com.example.baselibrary.mvp;

public interface BaseModel{
    interface DataCallBack<Data>{
        void onSuccess(Data data);
        void onCahceSuccess(Data data);
        void onError(String msg);
    }
}
