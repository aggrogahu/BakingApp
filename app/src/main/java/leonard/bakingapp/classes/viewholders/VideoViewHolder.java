package leonard.bakingapp.classes.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import leonard.bakingapp.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
//    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private MediaSource mMediaSource;
//    private MediaSessionCompat mMediaSession;
//    private PlaybackStateCompat.Builder mStateBuilder;



    public VideoViewHolder(View itemView) {
        super(itemView);
        mPlayerView = itemView.findViewById(R.id.step_exoPlayerView);
    }

//    public MediaSource getMediaSource() {
//        return mMediaSource;
//    }

    public void setMediaSource(MediaSource mMediaSource) {
        this.mMediaSource = mMediaSource;
    }

    public SimpleExoPlayerView getPlayerView() {
        return mPlayerView;
    }
}
