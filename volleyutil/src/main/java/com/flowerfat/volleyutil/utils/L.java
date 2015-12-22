package com.flowerfat.volleyutil.utils;

import android.util.Log;

/**
 * Created by 明明大美女 on 2015/12/22.
 */
public class L {
    public static boolean debug = false;

    public static void e(String msg)
    {
        if (debug)
        {
            Log.e("VolleyUtils", msg);
        }
    }
    public static void i(String msg)
    {
        if (debug)
        {
            Log.i("VolleyUtils", msg);
        }
    }
    public static void d(String msg)
    {
        if (debug)
        {
            Log.d("VolleyUtils", msg);
        }
    }
    public static void w(String msg)
    {
        if (debug)
        {
            Log.w("VolleyUtils", msg);
        }
    }
}