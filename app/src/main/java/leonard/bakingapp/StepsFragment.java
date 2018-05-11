package leonard.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.classes.Step;
import leonard.bakingapp.classes.viewholders.VideoViewHolder;

public class StepsFragment extends Fragment
//        implements ExoPlayer.EventListener
{
    public static final String STEP = "step";
//    public static final String LIST_INDEX = "list_index";
    public static final String ARG_OBJECT = "object";
    private static final String TAG = StepsFragment.class.getSimpleName();
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private Step mStep;
    private StepsAdapter mStepsAdapter;
    private RecyclerView mRecyclerView;

//    private List<Integer> mStepList;
//    private int mListIndex;

    public StepsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //TODO StepFragment load saved instance

        //TODO Inflate layout and set views accordingly (send dummy information to recyclerView adapter)
        Log.d(TAG, "onCreateView");


        View rootView = inflater.inflate(R.layout.fragment_recipe_steps,container,false);

        mRecyclerView = rootView.findViewById(R.id.recipe_step_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mPlayerView


        Bundle args = getArguments();
        mStep = null;
        if (args != null) {
            mStep = args.getParcelable(STEP);
        }

        List<String> stepObs = new ArrayList<>();
        stepObs.add(mStep.videoURL);
        stepObs.add(mStep.description);

//        TODO use recycler view and adapter to get reference to playerview
        mStepsAdapter = new StepsAdapter(stepObs,getContext());
        mRecyclerView.setAdapter(mStepsAdapter);
//        mRecyclerView.getChildAt(0);

//        mPlayerView = rootView.findViewById(R.id.step_exoPlayerView);

////        ((TextView) rootView.findViewById(R.id.step_dummy_text)).setText(
////                "Step " +
////                Integer.toString(args.getInt(ARG_OBJECT)) +
////        " " + mStep.shortDes);
////        getActivity().setTitle(mStep.shortDes);
//        mPlayerView = rootView.findViewById(R.id.test_exo);
//        initializeMediaSession();
//        String url = mStep.videoURL;

//        initializePlayer(Uri.parse(url));
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

//    public void initializePlayer(Uri uri) {
////        if (exoPlayer == null) {
////        mPlayerView = getView().findViewById(R.id.step_exoPlayer);
//        Context context = this.getContext();
////        mExoPlayer = exoPlayer;
//        // Create an instance of the ExoPlayer.
//        TrackSelector trackSelector = new DefaultTrackSelector();
//        LoadControl loadControl = new DefaultLoadControl();
//        mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
//        mPlayerView.setPlayer(mExoPlayer);
//
//        // Set the ExoPlayer.EventListener to this activity.
//        mExoPlayer.addListener(this);
//
//        // Prepare the MediaSource.
//        String userAgent = Util.getUserAgent(context, "Derp");
////        Uri mediaUri = Uri.parse(mStep.videoURL);
//        MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
//                context, userAgent), new DefaultExtractorsFactory(), null, null);
//        mExoPlayer.prepare(mediaSource);
//        mExoPlayer.setPlayWhenReady(false);
////        }
//    }

//    public void getPlayerView(){
//        View view = mRecyclerView.getChildAt(0);
//        Log.d(TAG, "getPlayerView: ");
//    }


//    @Override
//    public void onTimelineChanged(Timeline timeline, Object manifest) {
//
//    }
//
//    @Override
//    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
//
//    }
//
//    @Override
//    public void onLoadingChanged(boolean isLoading) {
//
//    }
//
//    @Override
//    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
//            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
//                    mExoPlayer.getCurrentPosition(), 1f);
//        } else if((playbackState == ExoPlayer.STATE_READY)){
//            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
//                    mExoPlayer.getCurrentPosition(), 1f);
//        }
//        mMediaSession.setPlaybackState(mStateBuilder.build());
////        showNotification(mStateBuilder.build());
//    }
//
//    @Override
//    public void onPlayerError(ExoPlaybackException error) {
//
//    }
//
//    @Override
//    public void onPositionDiscontinuity() {
//
//    }


    @Override
    public void onStart() {
        super.onStart();
//        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
//        mMediaSession.setActive(false);
    }

    @Override
    public void onPause() {
        super.onPause();
//        Log.d(TAG, "onPause");
    }


    private void releasePlayer() {
        Log.d(TAG, "releasePlayer");

//        RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
        SimpleExoPlayer exoPlayer = getExoPlayer();
        if (exoPlayer!= null){
//            VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
//            ExoPlayer exoPlayer = videoViewHolder.getPlayerView().getPlayer();
            exoPlayer.stop();
            exoPlayer.release();
        }
//
//        mExoPlayer.stop();
//        mExoPlayer.release();
//        mExoPlayer = null;
    }


    private SimpleExoPlayer getExoPlayer(){
        RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
        if (viewHolder instanceof VideoViewHolder) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
            return videoViewHolder.getPlayerView().getPlayer();
        }
        return null;
    }

    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }
}
