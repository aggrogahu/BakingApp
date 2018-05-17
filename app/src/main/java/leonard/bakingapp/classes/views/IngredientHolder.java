package leonard.bakingapp.classes.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import leonard.bakingapp.R;

public class IngredientHolder extends RecyclerView.ViewHolder {
    private TextView mQuantity, mIngredient;

    public IngredientHolder(View itemView) {
        super(itemView);
        mQuantity = itemView.findViewById(R.id.quantity_text_view);
        mIngredient = itemView.findViewById(R.id.ingredient_text_view);
    }

    public TextView getIngredient() {
        return mIngredient;
    }

    public TextView getQuantity() {
        return mQuantity;
    }

    public void setQuantity(TextView textView){
        mQuantity = textView;
    }
    public void setIngredient(TextView textView){
        mIngredient = textView;
    }
}
