package id.atumdev.anggaapplibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import id.atumdev.applib.socialmedia.facebook.FacebookShareDialogFragment;
import id.atumdev.applib.socialmedia.twitter.TwitterShareDialog;

public class MainActivity extends AppCompatActivity {
    public Button facebook;
    public Button twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facebook = (Button) findViewById(R.id.facebook_share);
        twitter = (Button) findViewById(R.id.twitter_share);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebookShare();
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwitterShare();
            }
        });
    }

    private void openFacebookShare() {
        final FacebookShareDialogFragment.Payload payload = new FacebookShareDialogFragment.Payload();
        payload.setDescription("Test description");
        payload.setLink("http://google.com");

        final FacebookShareDialogFragment dialog = FacebookShareDialogFragment.newInstance(payload);
        dialog.show(getSupportFragmentManager(), null);
    }

    private void openTwitterShare() {
        final TwitterShareDialog.Payload payload = new TwitterShareDialog.Payload();
        payload.setMessage("Test twitter api");
        payload.setLink("http://google.com/");
        TwitterShareDialog dialog = TwitterShareDialog.newInstance(payload);
        dialog.show(getFragmentManager(), null);
    }
}
