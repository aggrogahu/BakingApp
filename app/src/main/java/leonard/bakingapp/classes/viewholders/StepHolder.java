package leonard.bakingapp.classes.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import leonard.bakingapp.R;

public class StepHolder extends RecyclerView.ViewHolder {
    private TextView shortDescriptionTextView;
    private String shortDescription;

    public StepHolder(View itemView) {
        super(itemView);
        shortDescriptionTextView = itemView.findViewById(R.id.step_text_view);
        shortDescription = shortDescriptionTextView.getText().toString();
    }

    public TextView getShortDescription() {
        return shortDescriptionTextView;
    }

    public String getStringShortDescription(){ return shortDescription;}

    public void setShortDescription(String shortDesc){
        shortDescriptionTextView.setText(shortDesc);
    }
}
