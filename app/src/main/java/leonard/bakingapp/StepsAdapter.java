package leonard.bakingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
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
    private final String TAG = StepsAdapter.class.getSimpleName();
    private List<String> mStepObs;
    private SimpleExoPlayer mSimpleExoPlayer;
//    private final int SHORT_DESCRIPTION = 10;
    private final int DESCRIPTION = 11;
    private final int VIDEO = 12;
    private final int IMAGE = 13;
    private final int EMPTY = 69;
    private boolean areNotDelaying;
private Context mContext;



    public StepsAdapter(List<String> strings, Context context, Boolean bool){
        mStepObs = strings;
        mContext = context;
        areNotDelaying = bool;
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
//                Log.d(TAG, "onCreateViewHolder: vid");
                View videoView = inflater.inflate(R.layout.step_video,parent,false);
                viewHolder = new VideoViewHolder(videoView);
                break;
            case IMAGE:
                //TODO create image holder
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
//                holder.itemView.set
                break;
            case IMAGE:
//                Log.d(TAG, "onBindViewHolder: image");
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
//        Log.d(TAG, "bindVideoHolder: ");
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
//        Log.d(TAG, "bindVideoHolder: url = " + url);
        Uri mediaUri = Uri.parse(url);
        // create thumbnail
//        Bitmap bitmap = (url, MediaStore.Images.Thumbnails.MINI_KIND);
//        simpleExoPlayerView.setDefaultArtwork(bitmap);

        // Prepare the MediaSource.
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                mContext, userAgent), new DefaultExtractorsFactory(), null, null);
        videoViewHolder.setMediaSource(mediaSource);
//        if(areNotDelaying) {
            mExoPlayer.prepare(mediaSource);
//        }
//        mExoPlayer.setPlayWhenReady(false);
    }


//    public Bitmap customCreateVideoThumbnail(String filePath, int kind) {
//        Bitmap bitmap = null;
//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//
//            retriever.setDataSource(filePath, new HashMap<String, String>());
//            bitmap = retriever.getFrameAtTime(-1);
//
//        if (bitmap == null) return null;
//
//        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {
//            // Scale down the bitmap if it's too large.
//            int width = bitmap.getWidth();
//            int height = bitmap.getHeight();
//            int max = Math.max(width, height);
//            if (max > 512) {
//                float scale = 512f / max;
//                int w = Math.round(scale * width);
//                int h = Math.round(scale * height);
//                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
//            }
//        }
//        return bitmap;
//    }

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

    private void initializeMediaSession(){}

    private void initializeExoPlayer(){ }
}
