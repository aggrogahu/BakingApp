package leonard.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter {
    private List<Object> mStepList;
    private final int SHORT_DESCRIPTION = 10;
    private final int DESCRIPTION = 11;
    private final int VIDEO = 12;
    private final int IMAGE = 13;
    Context mContext;

    public StepsAdapter(List<Object> objects, Context context){
        mStepList = objects;
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //TODO monster switch statement to accommodate all types of media

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mStepList.get(position);


        return -1;
    }

    private void initializeMediaSession(){}

    private void initializeExoPlayer(){}
}
