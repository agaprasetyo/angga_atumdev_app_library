package id.atumdev.applib.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by Angga.Prasetiyo on 12/10/2015.
 */
public class DeviceUtil {
    private static final String TAG = DeviceUtil.class.getSimpleName();

    public static String getIMEI(Context context) {
        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tManager.getDeviceId();
    }

    public static String getManufacture() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }
}
