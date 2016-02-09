package id.atumdev.applib.devoptions.datastore;

import android.content.Context;
import android.support.annotation.NonNull;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Angga.Prasetiyo on 26/09/2015.
 */
public class DomainDataStore {
    private static final String TAG = DomainDataStore.class.getSimpleName();

    private static final String PREFERENCE_ATUM_DEV_DOMAIN = "PREFERENCE_ATUM_DEV_DOMAIN";
    private static final String PREFERENCE_ATUM_DEV_DOMAIN_URL = "PREFERENCE_ATUM_DEV_DOMAIN_URL";

    public static String getDomainUrl(Context context, @NonNull String defaultHost) {
        String domainSaved = context.getSharedPreferences(PREFERENCE_ATUM_DEV_DOMAIN,
                Context.MODE_PRIVATE).getString(PREFERENCE_ATUM_DEV_DOMAIN_URL, defaultHost);
        URI uri;
        try {
            uri = new URI(domainSaved);
            if (uri.getHost() != null) {
                return uri.toString();
            } else {
                return defaultHost;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return defaultHost;
        }
    }

    public static void saveDomainUrl(Context context, @NonNull String domain) {
        context.getSharedPreferences(PREFERENCE_ATUM_DEV_DOMAIN, Context.MODE_PRIVATE).edit()
                .putString(PREFERENCE_ATUM_DEV_DOMAIN_URL, domain)
                .apply();
    }
}
