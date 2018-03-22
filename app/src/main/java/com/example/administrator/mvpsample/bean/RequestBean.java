package com.example.administrator.mvpsample.bean;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class RequestBean {
    private Map<String,Object> req;

    public static RequestBean instance(){
        return new RequestBean();
    }

    private RequestBean() {
        this.req = new HashMap();
    }


    public RequestBean addReqParams(String key,Object value){
        req.put(key,value);
        return this;
    }
}
