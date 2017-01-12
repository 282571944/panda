package com.watermelon.luomi.panda.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by luomi on 2016-09-09.
 */
public class DeviceTool {

    /**
     * get device imei
     * @param context
     * @return
     */
    public static String getDeviceUni(Context context) {
        String deviceUni = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceUni = tm.getDeviceId();
        return deviceUni;
    }

}
