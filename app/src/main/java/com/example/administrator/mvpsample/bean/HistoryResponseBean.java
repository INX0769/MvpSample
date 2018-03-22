package com.example.administrator.mvpsample.bean;


import java.io.Serializable;
import java.util.List;

public class HistoryResponseBean implements Serializable{
    private int showapi_res_code;
    private String showapi_res_error;
    private Body showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public Body getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(Body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public class Body implements Serializable{
        private int ret_code;
        private List<HistoryBean> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<HistoryBean> getList() {
            return list;
        }

        public void setList(List<HistoryBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "ret_code=" + ret_code +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HistoryResponseBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }
}
