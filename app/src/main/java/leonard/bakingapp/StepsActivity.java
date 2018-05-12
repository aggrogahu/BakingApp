package leonard.bakingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

        //begin with the selected step

        mViewPager.setCurrentItem(position);

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
                //TODO initialize and set exoplayer to exoplayer view. Use only one instance of exoplayer to increase performance
                Log.d(TAG, "onPageSelected: change to " + position);

//                StepsFragment stepsFragment = (StepsFragment)mStepsPagerAdapter.getItem(position);
//                stepsFragment.;
//                stepsFragment.initializePlayer(mSimpleExoPlayer);

//                String tag = "android:switcher:" + mViewPager.getId() + ":" + position;
//                Log.d(TAG, "tag: " + tag);
//                StepsFragment stepsFragment = (StepsFragment) getSupportFragmentManager().findFragmentByTag(tag);
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

        //set

    }

    private void updateNavButtons(int position){
        Log.d(TAG, "updateNavButtons: position" + position);
        Log.d(TAG, "updateNavButtons: stepLength" + stepLength);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void nextFabButton(View view) {
        Log.d(TAG, "nextFabButton: ");
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    public void previousFabButton(View view) {
        Log.d(TAG, "previousFabButton: ");
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
    }

    //    private void attachPlayer() {
//        Log.d(TAG, "attachPlayer");
////        StepsFragment stepsFragment = (StepsFragment) mStepsPagerAdapter.getCurrentFragment();
////        stepsFragment.getPlayerView();
//    }

//    private List<Step> getStepArrayList(){
//        return new ArrayList<Step>(){{
//            add(new Step("Short Desc 01", "description", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", null));
//            add(new Step("Short Desc 02", "description", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4", null));
//            add(new Step("Short Desc 03", "description", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4", null));
//            add(new Step("Short Desc 04", "description", "URL", null));
//        }};
//    }
}
