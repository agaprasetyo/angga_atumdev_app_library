package id.atumdev.applib.devoptions.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angga.Prasetiyo on 28/09/2015.
 */
public class WsUrlModel implements Parcelable {
    private static final String TAG = WsUrlModel.class.getSimpleName();

    private String name;
    private String url;

    public WsUrlModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public WsUrlModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected WsUrlModel(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WsUrlModel> CREATOR =
            new Parcelable.Creator<WsUrlModel>() {
                @Override
                public WsUrlModel createFromParcel(Parcel in) {
                    return new WsUrlModel(in);
                }

                @Override
                public WsUrlModel[] newArray(int size) {
                    return new WsUrlModel[size];
                }
            };

    public class Builder {
        private String name;
        private String url;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public WsUrlModel build() {
            return new WsUrlModel(this.name, this.url);
        }
    }
}
