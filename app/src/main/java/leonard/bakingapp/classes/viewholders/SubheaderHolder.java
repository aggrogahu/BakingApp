package leonard.bakingapp.classes.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import leonard.bakingapp.R;

public class SubheaderHolder extends RecyclerView.ViewHolder {
    private TextView mSubheader;

    public SubheaderHolder(View itemView) {
        super(itemView);
        mSubheader = itemView.findViewById(R.id.detail_subheader_text_view);
    }

    public TextView getSubheader() {
        return mSubheader;
    }

    public void setSubheader(TextView textView){
        mSubheader = textView;
    }
}
