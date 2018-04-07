package leonard.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    String mQuantity;
    String mIngredient;

    public Ingredient(String quantity, String ingredient){
        this.mQuantity = quantity;
        this.mIngredient = ingredient;
    }

    private Ingredient(Parcel in) {
        mQuantity = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mIngredient);
    }
}
