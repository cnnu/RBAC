package cn.wolfcode.query;

public class JSONResult {

    private boolean success = true;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONResult mark(String msg){
        this.msg = msg;
        this.success = false;
        return this;
    }
}
