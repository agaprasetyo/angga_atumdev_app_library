package id.atumdev.applib.socialmedia.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class MetadataUtils {
    private MetadataUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getMetaString(final Context context, final String name) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return ai.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
