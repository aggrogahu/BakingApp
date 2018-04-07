package leonard.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {
    String mShortDes;
    String mDescription;
    String mVideoURL;
    String mThumbnailURL;

    public Step (String shortDesc, String description, String videoURL, String thumbnailURL){
        this.mShortDes = shortDesc;
        this.mDescription = description;
        this.mVideoURL = videoURL;
        this.mThumbnailURL = thumbnailURL;
    }

    private Step(Parcel in) {
        mShortDes = in.readString();
        mDescription = in.readString();
        mVideoURL = in.readString();
        mThumbnailURL = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mShortDes);
        dest.writeString(mDescription);
        dest.writeString(mVideoURL);
        dest.writeString(mThumbnailURL);
    }
}
