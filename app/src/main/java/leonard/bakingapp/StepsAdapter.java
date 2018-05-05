package leonard.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import leonard.bakingapp.data.Step;
import leonard.bakingapp.data.StepHolder;

public class StepsAdapter extends RecyclerView.Adapter {
    private List<String> mStepObs;
//    private final int SHORT_DESCRIPTION = 10;
    private final int DESCRIPTION = 11;
    private final int VIDEO = 12;
    private final int IMAGE = 13;
    Context mContext;



    public StepsAdapter(List<String> strings, Context context){
        mStepObs = strings;
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //TODO(1) monster switch statement to accommodate all types of media
        switch (viewType){
//            case SHORT_DESCRIPTION:
//                break;
            case DESCRIPTION:
                View stepView = inflater.inflate(R.layout.detail_step_item,parent,false);
                viewHolder = new StepHolder(stepView);
                break;
            case VIDEO:
                break;
            case IMAGE:
            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case DESCRIPTION:
                StepHolder stepHolder = (StepHolder) holder;
                buildDescHolder(stepHolder,position);
            default:
                break;
        }
    }

    private void buildDescHolder(StepHolder stepHolder, int position){
        String shortDes = mStepObs.get(position);
        stepHolder.setShortDescription(shortDes);
    }


    @Override
    public int getItemCount() {
        return mStepObs.size();
    }

    @Override
    public int getItemViewType(int position) {
        String item = mStepObs.get(position);
        if(item.contains(".mp4")){
            return VIDEO;
        } else if(item.contains(".jpeg") || item.contains(".jpg") || item.contains(".png")){
            return IMAGE;
        } else if (item != null){
            return DESCRIPTION;
        }


        return -1;
    }

    private void initializeMediaSession(){}

    private void initializeExoPlayer(){}
}
