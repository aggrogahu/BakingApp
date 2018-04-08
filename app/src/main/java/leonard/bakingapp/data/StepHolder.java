package leonard.bakingapp.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import leonard.bakingapp.R;

public class StepHolder extends RecyclerView.ViewHolder {
    private TextView mShortDescription;

    public StepHolder(View itemView) {
        super(itemView);
        mShortDescription = itemView.findViewById(R.id.step_text_view);
    }

    public TextView getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(TextView textView){
        mShortDescription = textView;
    }
}
