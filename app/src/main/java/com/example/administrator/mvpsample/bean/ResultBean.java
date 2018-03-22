package com.example.administrator.mvpsample.bean;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    public ResultMsgBean rsmsg;
    public T resp;
}
