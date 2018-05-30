package leonard.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.classes.Step;
import leonard.bakingapp.classes.views.VideoViewHolder;

public class StepsFragment extends Fragment {
    public static final String STEP = "step";
    public static final String AUTO_PLAY_POS = "autoplay";
    private static final String TAG = StepsFragment.class.getSimpleName();
    private static final String PLAY_WHEN_READY = "play_when_ready";
    //    private SimpleExoPlayer mExoPlayer;
//    private SimpleExoPlayerView mPlayerView;
//    private MediaSessionCompat mMediaSession;
    private RecyclerView mRecyclerView;

    private List<String> stepObs;
    private long playbackPosition;
    private boolean playWhenReady;

    public StepsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout and set views accordingly


        View rootView = inflater.inflate(R.layout.fragment_recipe_steps,container,false);

        mRecyclerView = rootView.findViewById(R.id.recipe_step_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Bundle args = getArguments();
        Step mStep = null;
        boolean playWhenReady = false;
        long playbackPosition =0;
        if (args != null) {
            mStep = args.getParcelable(STEP);
            playWhenReady = args.getBoolean("playWhenReady");
        }

        //  put step data into an object list for the adapter
        stepObs = new ArrayList<>();
        stepObs.add(mStep.videoURL);
        stepObs.add(mStep.thumbnailURL);
        stepObs.add(mStep.description);


        if(savedInstanceState!=null){
            Log.d(TAG, "onCreate: not null");
            playbackPosition = savedInstanceState.getLong(AUTO_PLAY_POS,0);
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY,false);
        }

        StepsAdapter mStepsAdapter = new StepsAdapter(stepObs, getContext(), playbackPosition,playWhenReady );
        mRecyclerView.setAdapter(mStepsAdapter);
        return rootView;
    }

//    public void setStepList(List<Integer> steps){ mStepList = steps;}
//
//    public void setListIndex(int index){mListIndex = index;}

//    public void initializeMediaSession(){
//        // Create a MediaSessionCompat.
//        mMediaSession = new MediaSessionCompat(this.getContext(), TAG);
//
//        // Enable callbacks from MediaButtons and TransportControls.
//        mMediaSession.setFlags(
//                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
//                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
//
//        // Do not let MediaButtons restart the player when the app is not visible.
//        mMediaSession.setMediaButtonReceiver(null);
//
//        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
//        mStateBuilder = new PlaybackStateCompat.Builder()
//                .setActions(
//                        PlaybackStateCompat.ACTION_PLAY |
//                                PlaybackStateCompat.ACTION_PAUSE |
//                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
//                                PlaybackStateCompat.ACTION_PLAY_PAUSE);
//
//        mMediaSession.setPlaybackState(mStateBuilder.build());
//
//
//        // MySessionCallback has methods that handle callbacks from a media controller.
//        mMediaSession.setCallback(new MySessionCallback());
//
//        // Start the Media Session since the activity is active.
//        mMediaSession.setActive(true);
//    }


    @Override
    public void onStart() {
        super.onStart();
        SimpleExoPlayer exoPlayer = getExoPlayer();
        if (exoPlayer!= null){
            RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
            if (viewHolder instanceof VideoViewHolder) {
                VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
                videoViewHolder.getPlayerView().setPlayer(newExoPlayer());
                videoViewHolder.getPlayerView().getPlayer().prepare(videoViewHolder.getMediaSource());
                videoViewHolder.getPlayerView().getPlayer().seekTo(playbackPosition);

            }
        }
    }

    private SimpleExoPlayer newExoPlayer(){
        // Create an instance of the ExoPlayer.
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        return ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        releasePlayer();
//        mMediaSession.setActive(false);
    }


    private void releasePlayer() {
        Log.d(TAG, "releasePlayer");



        SimpleExoPlayer exoPlayer = getExoPlayer();
        if (exoPlayer!= null){
            playbackPosition = exoPlayer.getCurrentPosition();
            playWhenReady = exoPlayer.getPlayWhenReady();
            exoPlayer.stop();
            exoPlayer.release();
        }

    }

    private SimpleExoPlayer getExoPlayer(){
        if (mRecyclerView.getChildCount()!=0) {
            RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
            if (viewHolder instanceof VideoViewHolder) {
                VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
                return videoViewHolder.getPlayerView().getPlayer();
            }
        }
        return null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(AUTO_PLAY_POS,playbackPosition);
        outState.putBoolean(PLAY_WHEN_READY,playWhenReady);
    }

    //    public void initializePlayer(){
//        Log.d(TAG, "initializePlayer: ");
//        if (stepObs != null) {
//            Log.d(TAG, "fragment: " + stepObs.get(0));
//        }
//        if(mRecyclerView != null){
//            Log.d(TAG, "loading: ");
//            RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
//            if (viewHolder instanceof VideoViewHolder) {
//                VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
//                videoViewHolder.getPlayerView().getPlayer().prepare(videoViewHolder.getMediaSource());
//
//            }
//        }
//
//    }


}
