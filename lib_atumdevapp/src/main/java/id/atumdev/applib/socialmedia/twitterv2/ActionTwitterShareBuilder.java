package id.atumdev.applib.socialmedia.twitterv2;

import android.app.Activity;

/**
 * Created by Angga.Prasetiyo on 30/09/2015.
 */
public class ActionTwitterShareBuilder {
    private static final String TAG = ActionTwitterShareBuilder.class.getSimpleName();

    private String consumerKey;
    private String consumerSecretKey;
    private Activity activity;
    private TwitterApp.TwDialogListener dialogListener;
    private TweetStatusListener tweetStatusListener;

    public ActionTwitterShareBuilder() {
    }

    public ActionTwitterShareBuilder setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
        return this;
    }

    public ActionTwitterShareBuilder setConsumerSecretKey(String consumerSecretKey) {
        this.consumerSecretKey = consumerSecretKey;
        return this;
    }

    public ActionTwitterShareBuilder setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public ActionTwitterShareBuilder setDialogListener(TwitterApp.TwDialogListener dialogListener) {
        this.dialogListener = dialogListener;
        return this;
    }

    public ActionTwitterShareBuilder setTweetStatusListener(TweetStatusListener tweetStatusListener) {
        this.tweetStatusListener = tweetStatusListener;
        return this;
    }

    public ActionTwitterShare build() {
        return new ActionTwitterShare(this.activity, this.consumerKey, this.consumerSecretKey, this.dialogListener, this.tweetStatusListener);
    }
}
