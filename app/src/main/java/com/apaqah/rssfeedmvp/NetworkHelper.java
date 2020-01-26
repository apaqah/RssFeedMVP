package com.apaqah.rssfeedmvp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

public class NetworkHelper {

    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager cm = getConnectionManager(context);
            if (cm != null) {
                NetworkInfo ni = getNetworkInfo(cm);
                if (ni != null && ni.isConnected())
                    if (ni.getState() == NetworkInfo.State.CONNECTED)
                        return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static void showErrorMessageOnUiThread(final Context context, String errorMessage) {
        Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(context, String.valueOf(msg.obj), Toast.LENGTH_SHORT).show();
            }
        };
        Message message = mHandler.obtainMessage(-1, errorMessage);
        message.sendToTarget();
    }

    private static ConnectivityManager getConnectionManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private static NetworkInfo getNetworkInfo(ConnectivityManager connectivityManager) {
        return connectivityManager.getActiveNetworkInfo();
    }

}
