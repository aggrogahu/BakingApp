package leonard.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import leonard.bakingapp.data.StepHolder;
import leonard.bakingapp.data.VideoViewHolder;

public class StepsAdapter extends RecyclerView.Adapter {
    private final String TAG = StepsAdapter.class.getSimpleName();
    private List<String> mStepObs;
    private SimpleExoPlayer mSimpleExoPlayer;
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
                Log.d(TAG, "onCreateViewHolder: Desc created");
                View stepView = inflater.inflate(R.layout.detail_step_item,parent,false);
                viewHolder = new StepHolder(stepView);
                break;
            case VIDEO:
                Log.d(TAG, "onCreateViewHolder: Video view created");
                View videoView = inflater.inflate(R.layout.step_video,parent,false);
                viewHolder = new VideoViewHolder(videoView);
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
                bindDescHolder(stepHolder,position);
                break;
            case VIDEO:
                VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            default:
                break;
        }
    }

    private void bindDescHolder(StepHolder stepHolder, int position){
        String shortDes = mStepObs.get(position);
        stepHolder.setShortDescription(shortDes);
    }
    private void bindVideoHolder(VideoViewHolder videoViewHolder,int position){
        // Prepare the MediaSource.
        String userAgent = Util.getUserAgent(mContext, "Derp");
        String videoUrl = mStepObs.get(position);
        Log.d(TAG, "videoUrl: " + videoUrl);
        Uri mediaUri = Uri.parse(videoUrl);
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                mContext, userAgent), new DefaultExtractorsFactory(), null, null);
        videoViewHolder.setMediaSource(mediaSource);
    }


    @Override
    public int getItemCount() {
        return mStepObs.size();
    }

    @Override
    public int getItemViewType(int position) {
        String item = mStepObs.get(position);
        if(item.contains(".mp4")){
//            Log.d(TAG, "getItemViewType: Video detected");
            return VIDEO;
        } else if(item.contains(".jpeg") || item.contains(".jpg") || item.contains(".png")){
            return IMAGE;
        } else {
//            Log.d(TAG, "getItemViewType: Text detected");
            return DESCRIPTION;
        }
    }

    private void initializeMediaSession(){}

    private void initializeExoPlayer(){}
}
