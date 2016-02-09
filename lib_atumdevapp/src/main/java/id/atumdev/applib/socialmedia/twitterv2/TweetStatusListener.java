package id.atumdev.applib.socialmedia.twitterv2;

import twitter4j.Status;

/**
 * Created by Angga.Prasetiyo on 30/09/2015.
 */
public interface TweetStatusListener {

    void onSuccess(Status successResponse);

    void onError(String errorResponse);
}
