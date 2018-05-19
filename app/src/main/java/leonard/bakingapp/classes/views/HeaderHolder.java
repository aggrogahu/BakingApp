package leonard.bakingapp.classes.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import leonard.bakingapp.R;

public class HeaderHolder extends RecyclerView.ViewHolder {
    private TextView mRecipeName;
    private ImageView mRecipeImage;


    public HeaderHolder(View itemView) {
        super(itemView);
        mRecipeImage = itemView.findViewById(R.id.detail_image_view);
        mRecipeName = itemView.findViewById(R.id.detail_header_text_view);
    }

    public ImageView getRecipeImage() {
        return mRecipeImage;
    }

    public TextView getRecipeName() {
        return mRecipeName;
    }

    public void setRecipeImage(ImageView mRecipeImage) {
        this.mRecipeImage = mRecipeImage;
    }

    public void setRecipeName(TextView mRecipeName) {
        this.mRecipeName = mRecipeName;
    }
}
