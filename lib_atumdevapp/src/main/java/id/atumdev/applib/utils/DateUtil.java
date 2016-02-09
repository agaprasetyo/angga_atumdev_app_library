package id.atumdev.applib.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ANGGA on 10/12/2015.
 */
public class DateUtil {

    public static String instanceDateFormat(String format, Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat(format, Locale.getDefault());
        return fmt.format(date);
    }

    public static String instanceDateFormat(String format, Date date, Locale locale) {
        SimpleDateFormat fmt = new SimpleDateFormat(format, locale);
        return fmt.format(date);
    }
}
