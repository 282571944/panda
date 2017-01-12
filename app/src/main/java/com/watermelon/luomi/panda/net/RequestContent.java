package com.watermelon.luomi.panda.net;

/**
 * Created by luomi on 2016-09-12.
 */
public class RequestContent {

    RequestHeader header;
    RequestBody body;

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public RequestBody getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }

    public class RequestHeader {
        String appcode = "3E";//(默认使用:3E)
        String languagetype = "zh-cn";//响应语言类型（默认使用: zh-cn）
        String devicetype = "android";//设备系统类型
        String devicemodel = "phone";//设备物理类型
        String sys = "";//设备出厂名称
        String sysversion = "";//设备版本号
        String deviceidentifier = "";//设备唯一识别码
        String service = "";//服务器请求
        String action = "";//请求行为
        String page = "";//当前页面编号
        String pagesize = "16";//页面显示内容大小(默认使用:16)
        String noncestr = "";//无理6位字符串
        String timestamp = "";//时间戳(毫秒数)
        String sign = "";//noncestr, timestamp, appcode, service, action(Sorts the specified array in ascending natural order.) MD5加密
        String appversion = "";//应用版本号
        String token = "";//身份有效记号(登录以后使用)

        public String getAppcode() {
            return appcode;
        }

        public void setAppcode(String appcode) {
            this.appcode = appcode;
        }

        public String getLanguagetype() {
            return languagetype;
        }

        public void setLanguagetype(String languagetype) {
            this.languagetype = languagetype;
        }

        public String getDevicetype() {
            return devicetype;
        }

        public void setDevicetype(String devicetype) {
            this.devicetype = devicetype;
        }

        public String getDevicemodel() {
            return devicemodel;
        }

        public void setDevicemodel(String devicemodel) {
            this.devicemodel = devicemodel;
        }

        public String getSys() {
            return sys;
        }

        public void setSys(String sys) {
            this.sys = sys;
        }

        public String getSysversion() {
            return sysversion;
        }

        public void setSysversion(String sysversion) {
            this.sysversion = sysversion;
        }

        public String getDeviceidentifier() {
            return deviceidentifier;
        }

        public void setDeviceidentifier(String deviceidentifier) {
            this.deviceidentifier = deviceidentifier;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPagesize() {
            return pagesize;
        }

        public void setPagesize(String pagesize) {
            this.pagesize = pagesize;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getAppversion() {
            return appversion;
        }

        public void setAppversion(String appversion) {
            this.appversion = appversion;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public class RequestBody {
        String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
