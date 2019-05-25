package com.mohmedhassan.cleaningapp.HTTP_POST;

import java.util.HashMap;

public class HttpCall_Post {

    public static final int POST = 1;
    public static final int GET = 2;


    private String url;
    private int methodtype;
    private HashMap<String,String> params ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethodtype() {
        return methodtype;
    }

    public void setMethodtype(int methodtype) {
        this.methodtype = methodtype;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
