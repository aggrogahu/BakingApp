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

import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.classes.Step;
import leonard.bakingapp.classes.views.VideoViewHolder;

public class StepsFragment extends Fragment {
    public static final String STEP = "step";
    public static final String ARG_OBJECT = "object";
    private static final String TAG = StepsFragment.class.getSimpleName();
//    private SimpleExoPlayer mExoPlayer;
//    private SimpleExoPlayerView mPlayerView;
//    private MediaSessionCompat mMediaSession;
    private RecyclerView mRecyclerView;

    private List<String> stepObs;

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
        Boolean areNotDelaying = false;
        if (args != null) {
            mStep = args.getParcelable(STEP);
            areNotDelaying = args.getBoolean("areNotDelaying");
        }

        //  put step data into an object list for the adapter
        stepObs = new ArrayList<>();
        stepObs.add(mStep.videoURL);
        stepObs.add(mStep.thumbnailURL);
        stepObs.add(mStep.description);


        StepsAdapter mStepsAdapter = new StepsAdapter(stepObs, getContext(), areNotDelaying);
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
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
//        mMediaSession.setActive(false);
    }


    private void releasePlayer() {
        Log.d(TAG, "releasePlayer");

        SimpleExoPlayer exoPlayer = getExoPlayer();
        if (exoPlayer!= null){
            exoPlayer.stop();
            exoPlayer.release();
        }

    }

    private SimpleExoPlayer getExoPlayer(){
        RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
        if (viewHolder instanceof VideoViewHolder) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
            return videoViewHolder.getPlayerView().getPlayer();
        }
        return null;
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
