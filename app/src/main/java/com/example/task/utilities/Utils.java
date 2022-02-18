package com.example.task.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
    public static Boolean NoInternetConnection(Context context) {
        Boolean isWifiConn = false, isMobileConn = false;
        try {
            ConnectivityManager cMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cMgr.getActiveNetworkInfo();
            if (networkInfo != null) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = networkInfo.isConnected();
                }
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = networkInfo.isConnected();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (!isWifiConn && !isMobileConn);
    }
}
