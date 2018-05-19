package leonard.bakingapp.classes.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import leonard.bakingapp.R;

public class ImageHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;

    public ImageHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.step_image_view);
    }

    public ImageView getmImageView() {
        return mImageView;
    }
}
