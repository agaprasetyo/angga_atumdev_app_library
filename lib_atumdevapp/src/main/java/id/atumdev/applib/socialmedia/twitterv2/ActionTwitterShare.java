package id.atumdev.applib.socialmedia.twitterv2;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by Angga.Prasetiyo on 30/09/2015.
 */
public class ActionTwitterShare {
    private static final String TAG = ActionTwitterShare.class.getSimpleName();

    private final Activity activity;
    private final TwitterApp twitterApp;
    private final TweetStatusListener tweetStatusListener;

    public ActionTwitterShare(Activity activity, String consumerKey, String consumerSecretKey, TwitterApp.TwDialogListener dialogListener, TweetStatusListener tweetStatusListener) {
        this.activity = activity;
        this.tweetStatusListener = tweetStatusListener;
        twitterApp = new TwitterApp(activity, consumerKey, consumerSecretKey);
        twitterApp.setListener(dialogListener);
    }

    public void tweetText(final String text) {
        if (twitterApp.hasAccessToken()) {
            new AsyncTask<String, String, Void>() {
                @Override
                protected Void doInBackground(String... params) {
                    twitterApp.updateStatusWithListener(text, tweetStatusListener);
                    return null;
                }
            }.execute();
        } else {
            twitterApp.authorize();
        }
    }

    public void clearAllAccessToken() {
        twitterApp.resetAccessToken();
    }

}
