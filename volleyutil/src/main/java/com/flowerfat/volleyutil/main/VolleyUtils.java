package com.flowerfat.volleyutil.main;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.flowerfat.volleyutil.https.HttpsUtils;
import com.flowerfat.volleyutil.utils.L;

import java.io.InputStream;

/**
 * Created by Bigflower on 2015/12/15.
 */
public class VolleyUtils {

    public static final int DEFAULT_MILLISECONDS = 20 * 1000;
    public static String Decode = null ;

    private static VolleyUtils mInstance;
    private RequestQueue mRequestQueue;

    private SharedPreferences cookiePrefs;
    private boolean isAutoCookie = false;

    public static VolleyUtils getInstance() {
        if (mInstance == null) {
            Class var0 = VolleyUtils.class;
            synchronized (VolleyUtils.class) {
                if (mInstance == null) {
                    mInstance = new VolleyUtils();
                }
            }
        }
        return mInstance;
    }
    /////////////////////////////////////
    // init
    public void init(Context context) {
        init(context, false);
    }

    public void init(Context context, boolean isAutoCookie) {
        mRequestQueue = Volley.newRequestQueue(context);
        cookiePrefs = context.getSharedPreferences("CookiePrefsFile", 0);
        this.isAutoCookie = isAutoCookie;
    }

    public void init(Context context, InputStream... certificates) {
        HurlStack hurlStack = new HurlStack(null, HttpsUtils.initCertificates(null, null, certificates));
        mRequestQueue = Volley.newRequestQueue(context, hurlStack);
        cookiePrefs = context.getSharedPreferences("CookiePrefsFile", 0);
    }

    public void init(Context context, InputStream bksFile, String password, InputStream... certificates) {
        HurlStack hurlStack = new HurlStack(null, HttpsUtils.initCertificates(bksFile, password, certificates));
        mRequestQueue = Volley.newRequestQueue(context, hurlStack);
        cookiePrefs = context.getSharedPreferences("CookiePrefsFile", 0);
    }

    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    public void cancelAll() {
        mRequestQueue.cancelAll(this);
    }


    /////////////////////////////////////
    // four request ways
    public VolleyBuilder get() {
        return new VolleyBuilder();
    }

    public VolleyBuilder get(String url) {
        return new VolleyBuilder(Request.Method.GET, url);
    }

    public VolleyBuilder post() {
        return new VolleyBuilder(Request.Method.POST);
    }

    public VolleyBuilder post(String url) {
        return new VolleyBuilder(Request.Method.POST, url);
    }

    public VolleyBuilder delete() {
        return new VolleyBuilder(Request.Method.DELETE);
    }

    public VolleyBuilder delete(String url) {
        return new VolleyBuilder(Request.Method.DELETE, url);
    }

    public VolleyBuilder put() {
        return new VolleyBuilder(Request.Method.PUT);
    }

    public VolleyBuilder put(String url) {
        return new VolleyBuilder(Request.Method.PUT, url);
    }

    /////////////////////////////////////
    // request start
    public void build(Request request) {
        mRequestQueue.add(request);
    }

    /////////////////////////////////////
    // cookie
    public void setAutoCookie(boolean isAutoCookie) {
        this.isAutoCookie = isAutoCookie;
    }

    public void setCookie(String cookie) {
        if (isAutoCookie)
            cookiePrefs.edit().putString("cookie", cookie).apply();
    }

    public String getCookie() {
        if (isAutoCookie)
            return cookiePrefs.getString("cookie", null);
        else {
            return null;
        }
    }

    public void clearCookie() {
        cookiePrefs.edit().remove("cookie").apply();
    }

    /////////////////////////////////////
    // log
    public void debug(boolean isDebug){
        L.debug = isDebug ;
    }
}
