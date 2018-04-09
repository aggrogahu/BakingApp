package leonard.bakingapp.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import leonard.bakingapp.R;

public class StepHolder extends RecyclerView.ViewHolder {
    private TextView mShortDescription;
    private String shortDescription;

    public StepHolder(View itemView) {
        super(itemView);
        mShortDescription = itemView.findViewById(R.id.step_text_view);
        shortDescription = mShortDescription.getText().toString();
    }

    public TextView getShortDescription() {
        return mShortDescription;
    }

    public String getStringShortDescription(){ return shortDescription;}

    public void setShortDescription(TextView textView){
        mShortDescription = textView;
    }
}
