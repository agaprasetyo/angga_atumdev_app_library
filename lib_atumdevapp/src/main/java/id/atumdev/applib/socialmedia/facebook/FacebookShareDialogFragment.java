/*
 * Copyright (c) 2013 Noveo Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Except as contained in this notice, the name(s) of the above copyright holders
 * shall not be used in advertising or otherwise to promote the sale, use or
 * other dealings in this Software without prior written authorization.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package id.atumdev.applib.socialmedia.facebook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import id.atumdev.applib.socialmedia.utils.BundleUtils;


public class FacebookShareDialogFragment extends DialogFragment {

    private static final String FRAGMENT_TAG = "facebook_share_dialog";
    private Payload payload;
    public CallbackManager callbackManager;

    public static FacebookShareDialogFragment newInstance(final Payload payload) {
        if (payload == null) {
            throw new IllegalArgumentException("payload can't be null");
        }

        final FacebookShareDialogFragment fragment = new FacebookShareDialogFragment();
        final Bundle arguments = new Bundle();
        BundleUtils.putPayload(arguments, payload);
        fragment.setArguments(arguments);

        return fragment;
    }

    public Payload getPayload() {
        return payload;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleUtils.putPayload(outState, payload);
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = getArguments();
        payload = (Payload) BundleUtils.getPayload(arguments);

        setShowsDialog(false);

        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            payload = (Payload) BundleUtils.getPayload(savedInstanceState);
        } else {
            final AccessToken session = AccessToken.getCurrentAccessToken();

            if (session != null && !session.isExpired()) {
                share();
            }
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, requestCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void onShareFinished() {
        dismiss();
    }

    private void share() {
        new ShareDialog(getActivity()).show(getShareContent(), ShareDialog.Mode.WEB);
    }

    private ShareContent getShareContent() {
        return new ShareLinkContent.Builder()
                .setContentTitle(payload.getName())
                .setContentDescription(payload.getDescription())
                .setContentUrl(Uri.parse(payload.getLink()))
                .build();
    }

    @Override
    public void show(final FragmentManager manager, final String tag) {
        final Fragment fragment = manager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
            manager.beginTransaction()
                    .remove(fragment)
                    .commit();
            manager.executePendingTransactions();
        }
        super.show(manager, tag != null ? tag : FRAGMENT_TAG);
    }

    @Override
    public int show(final FragmentTransaction transaction, final String tag) {
        return super.show(transaction, tag != null ? tag : FRAGMENT_TAG);
    }


    public static final class Payload implements id.atumdev.applib.socialmedia.models.Payload {
        private String link;
        private String caption;
        private String description;
        private String name;
        private String picture;

        public String getLink() {
            return link;
        }

        public void setLink(final String link) {
            this.link = link;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(final String caption) {
            this.caption = caption;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(final String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(final String picture) {
            this.picture = picture;
        }
    }
}
