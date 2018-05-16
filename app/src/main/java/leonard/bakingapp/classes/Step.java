package leonard.bakingapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {
    public String shortDescription;
    public String description;
    public String videoURL;
    public String thumbnailURL;

    public Step(Parcel in) {
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    public Step(String shortDesc, String desc, String vidUrl, String thumbUrl) {
        shortDescription = shortDesc;
        description = desc;
        videoURL = vidUrl;
        thumbnailURL = thumbUrl;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
