package id.atumdev.applib.socialmedia.twitterv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import twitter4j.auth.AccessToken;

/**
 * Created by ANGGA PRASETIYO on 9/30/2015.
 */
public class TwitterSession {

    private SharedPreferences sharedPref;
    private Editor editor;

    private static final String TWEET_AUTH_KEY = "TWEET_AUTH_KEY";
    private static final String TWEET_AUTH_SECRET_KEY = "TWEET_AUTH_SECRET_KEY";
    private static final String TWEET_USER_NAME = "TWEET_USER_NAME";
    private static final String SHARED_TWITTER_LOGIN = "SHARED_TWITTER_LOGIN";

    public TwitterSession(Context context) {
        sharedPref 	  = context.getSharedPreferences(SHARED_TWITTER_LOGIN, Context.MODE_PRIVATE);

        editor 		  = sharedPref.edit();
    }

    public void storeAccessToken(AccessToken accessToken, String username) {
        editor.putString(TWEET_AUTH_KEY, accessToken.getToken());
        editor.putString(TWEET_AUTH_SECRET_KEY, accessToken.getTokenSecret());
        editor.putString(TWEET_USER_NAME, username);

        editor.commit();
    }

    public void resetAccessToken() {
        editor.putString(TWEET_AUTH_KEY, null);
        editor.putString(TWEET_AUTH_SECRET_KEY, null);
        editor.putString(TWEET_USER_NAME, null);

        editor.commit();
    }

    public String getUsername() {
        return sharedPref.getString(TWEET_USER_NAME, "");
    }

    public AccessToken getAccessToken() {
        String token 		= sharedPref.getString(TWEET_AUTH_KEY, null);
        String tokenSecret 	= sharedPref.getString(TWEET_AUTH_SECRET_KEY, null);

        if (token != null && tokenSecret != null)
            return new AccessToken(token, tokenSecret);
        else
            return null;
    }
}
