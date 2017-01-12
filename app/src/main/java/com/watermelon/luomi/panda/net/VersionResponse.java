package com.watermelon.luomi.panda.net;

/**
 * Created by luomi on 2016-09-19.
 */
public class VersionResponse {
    String status;
    String latest;
    String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
