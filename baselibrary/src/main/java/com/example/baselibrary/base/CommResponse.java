package com.example.baselibrary.base;

/**
 * 通用返回实体示例（具体属性名根据具体调整）
 * @param <Data>内容体,根据后台数据结构传入实体
 */
public class CommResponse<Data> {
    private State state;
    private Data data;
    public static final int CODE_SUCCESS=101;//结果码

    public CommResponse(State state, Data data) {
        this.state = state;
        this.data = data;
    }

    /**
     * 鉴定结果码
     * @param res
     * @return
     */
    public static boolean isSuccess(CommResponse res){
        return res!=null&&res.getState()!=null&&CODE_SUCCESS==res.getState().getCode();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class State{
        private int code;
        private String msg;

        public State(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
