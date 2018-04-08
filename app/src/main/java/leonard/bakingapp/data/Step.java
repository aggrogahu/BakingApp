package leonard.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {
    public String shortDes;
    public String description;
    public String videoURL;
    public String thumbnailURL;

    public Step (String shortDesc, String description, String videoURL, String thumbnailURL){
        this.shortDes = shortDesc;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    private Step(Parcel in) {
        shortDes = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
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
        dest.writeString(shortDes);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }
}
