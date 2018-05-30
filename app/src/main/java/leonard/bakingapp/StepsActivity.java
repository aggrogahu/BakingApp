package leonard.bakingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import java.util.List;

import leonard.bakingapp.classes.Step;

public class StepsActivity extends AppCompatActivity {
    private static final String TAG = StepsActivity.class.getSimpleName();
    StepsPagerAdapter mStepsPagerAdapter;
    ViewPager mViewPager;
    private FloatingActionButton rightFAB, leftFAB;
    private boolean isLandscape;
    private int stepLength;
//    private SimpleExoPlayer mSimpleExoPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_step_detail);
        int orientation = getResources().getConfiguration().orientation;
        int position = getIntent().getIntExtra("selectedStep", 0);
        leftFAB = findViewById(R.id.fableft);
        rightFAB = findViewById(R.id.fabright);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
          //if landscape && step contains video, hide tick marks and action bar, and display button navigation
            isLandscape = true;
            getSupportActionBar().hide();
            findViewById(R.id.tick_marks).setVisibility(View.GONE);
            if(position != 0){leftFAB.setVisibility(View.VISIBLE);}
            rightFAB.setVisibility(View.VISIBLE);
        }
        else {
            isLandscape = false;
        }

//        //initialize exoPlayer
//        TrackSelector trackSelector = new DefaultTrackSelector();
//        LoadControl loadControl = new DefaultLoadControl();
//        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        //setup ViewPager adapter and tick marks (tabLayout)
        mStepsPagerAdapter = new StepsPagerAdapter(getSupportFragmentManager(), getIntent().<Step>getParcelableArrayListExtra("stepList"));
        mViewPager = findViewById(R.id.step_pager);
        mViewPager.setAdapter(mStepsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tick_marks);
        tabLayout.setupWithViewPager(mViewPager);



        //set title
        final List<Step> stepList = getIntent().getParcelableArrayListExtra("stepList");
        stepLength = stepList.size()-1;
        setTitle(stepList.get(position).shortDescription);

        //set listener
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //TODO initialize and set exoplayer to exoplayer view-- use only one instance of exoplayer to increase performance

//                StepsFragment stepsFragment = (StepsFragment)mStepsPagerAdapter.getItem(position);
//                stepsFragment.initializePlayer();
//                stepsFragment.initializePlayer(mSimpleExoPlayer);

//                SimpleExoPlayerView exoPlayerView = stepsFragment.getView().findViewById(R.id.step_exoPlayerView);
//                StepsFragment stepsFragment = (StepsFragment) mStepsPagerAdapter.getCurrentFragment();
//                SimpleExoPlayerView exoPlayerView = stepsFragment.getView().findViewById(R.id.step_exoPlayerView);
//                stepsFragment.initializeMediaSession();
//                stepsFragment.initializePlayer(mSimpleExoPlayer, exoPlayerView);
                updateNavButtons(position);
                setTitle(stepList.get(position).shortDescription);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //begin with the selected step
        mViewPager.setCurrentItem(position);

    }

    /**
     * high visibility of the navigation buttons according to position in the viewpager
     * @param position  position in the viewpager
     */
    private void updateNavButtons(int position){
        if(isLandscape){
            if(position==0){
                rightFAB.setVisibility(View.VISIBLE);
                leftFAB.setVisibility(View.INVISIBLE);
            } else if (position==stepLength){
                rightFAB.setVisibility(View.INVISIBLE);
                leftFAB.setVisibility(View.VISIBLE);
            } else {
                rightFAB.setVisibility(View.VISIBLE);
                leftFAB.setVisibility(View.VISIBLE);
            }
        }
    }

    public void nextFabButton(View view) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    public void previousFabButton(View view) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
    }

    //    private void attachPlayer() {
//        Log.d(TAG, "attachPlayer");
////        StepsFragment stepsFragment = (StepsFragment) mStepsPagerAdapter.getCurrentFragment();
////        stepsFragment.getPlayerView();
//    }

}
