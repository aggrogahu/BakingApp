package leonard.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import leonard.bakingapp.classes.views.ImageHolder;
import leonard.bakingapp.classes.views.StepHolder;
import leonard.bakingapp.classes.views.VideoViewHolder;

public class StepsAdapter extends RecyclerView.Adapter {
//    private final String TAG = StepsAdapter.class.getSimpleName();
    private List<String> mStepObs;
//    private SimpleExoPlayer mSimpleExoPlayer;
//    private final int SHORT_DESCRIPTION = 10;
    private final int DESCRIPTION = 11;
    private final int VIDEO = 12;
    private final int IMAGE = 13;
    private final int EMPTY = 69;
    private long mPlaybackPosition;
    private boolean areNotDelaying; // functionality is not yet implemented
    private boolean playWhenReady;
private Context mContext;



    public StepsAdapter(List<String> strings, Context context, long playbackPosition, boolean bool){
        mStepObs = strings;
        mContext = context;
        mPlaybackPosition = playbackPosition;
        playWhenReady = bool;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case EMPTY:
                View emptyView = inflater.inflate(R.layout.empty,parent,false);
                viewHolder = new RecyclerView.ViewHolder(emptyView) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
                break;
            case DESCRIPTION:
                View stepView = inflater.inflate(R.layout.detail_step_item,parent,false);
                viewHolder = new StepHolder(stepView);
                break;
            case VIDEO:
                View videoView = inflater.inflate(R.layout.step_video,parent,false);
                viewHolder = new VideoViewHolder(videoView);
                break;
            case IMAGE:
                // original Json doesn't have a images but this handles images if it did
                View imageView = inflater.inflate(R.layout.step_image,parent,false);
                viewHolder = new ImageHolder(imageView);
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
                bindVideoHolder(videoViewHolder,position);
                break;
            case IMAGE:
                ImageHolder imageHolder = (ImageHolder) holder;
                bindImageHolder(imageHolder,position);
            default:
                break;
        }
    }

    private void bindImageHolder(ImageHolder imageHolder, int position) {
        String url = mStepObs.get(position);
        Picasso.get().load(url).into(imageHolder.getmImageView());
    }

    private void bindDescHolder(StepHolder stepHolder, int position){
        String shortDes = mStepObs.get(position);
        stepHolder.setShortDescription(shortDes);
    }
    private void bindVideoHolder(VideoViewHolder videoViewHolder,int position){
        // Create an instance of the ExoPlayer.
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        SimpleExoPlayer mExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, loadControl);
        SimpleExoPlayerView simpleExoPlayerView = videoViewHolder.getPlayerView();
        simpleExoPlayerView.setPlayer(mExoPlayer);

        // Set the ExoPlayer.EventListener to this activity.
//        mExoPlayer.addListener(this);


//        //TODO pass a cached data source
        String userAgent = Util.getUserAgent(mContext, "BakingApp");
        String url = mStepObs.get(position);
        Uri mediaUri = Uri.parse(url);

        // Prepare the MediaSource.
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                mContext, userAgent), new DefaultExtractorsFactory(), null, null);
        videoViewHolder.setMediaSource(mediaSource);
//        if(areNotDelaying) {
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.seekTo(mPlaybackPosition);
            mExoPlayer.setPlayWhenReady(playWhenReady);
//        }
//        mExoPlayer.setPlayWhenReady(false);
    }

    @Override
    public int getItemCount() {
        return mStepObs.size();
    }

    @Override
    public int getItemViewType(int position) {
        String item = mStepObs.get(position);
        if (item.equals("")) {
            return EMPTY;
        }
        else if(item.contains(".mp4")){
            return VIDEO;
        } else if(item.contains(".jpeg") || item.contains(".jpg") || item.contains(".png")){
            return IMAGE;
        } else {
            return DESCRIPTION;
        }
    }
//
//    private void initializeMediaSession(){}
//
//    private void initializeExoPlayer(){ }
}
