package id.atumdev.applib.utils;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by ANGGA PRASETIYO on 9/26/2015.
 */
public class Logger {

    public static void debug(@NonNull String tag, @NonNull String message) {
        Log.d("ANGGA PRASETIYO => " + tag, message);
    }
}
