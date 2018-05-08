package leonard.bakingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.data.Step;

public class StepsActivity extends AppCompatActivity {
    private static final String TAG = StepsActivity.class.getSimpleName();
    //TODO might have to duplicate this code in recipe detail activity for two pane
    StepsPagerAdapter mStepsPagerAdapter;
    ViewPager mViewPager;
    private SimpleExoPlayer mSimpleExoPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
          //TODO if landscape && step contains video, hide tick marks and action bar, and display button navigation
            getSupportActionBar().hide();
            findViewById(R.id.tick_marks).setVisibility(View.GONE);
        }
//        else if (orientation == Configuration.ORIENTATION_PORTRAIT){
//
//        }

        //initialize exoplayer
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        //setup ViewPager adapter and tick marks (tabLayout)
        mStepsPagerAdapter = new StepsPagerAdapter(getSupportFragmentManager(), getIntent().<Step>getParcelableArrayListExtra("stepList"));
        mViewPager = findViewById(R.id.step_pager);
        mViewPager.setAdapter(mStepsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tick_marks);
        tabLayout.setupWithViewPager(mViewPager);

        //begin with the selected step
        int position = getIntent().getIntExtra("selectedStep", 0);
        mViewPager.setCurrentItem(position);
        final List<Step> stepList = getIntent().getParcelableArrayListExtra("stepList");
        setTitle(stepList.get(position).shortDes);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //TODO(1) initialize and set exoplayer to exoplayer view
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

                setTitle(stepList.get(position).shortDes);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //set

    }

//    private List<Step> getStepArrayList(){
//        return new ArrayList<Step>(){{
//            add(new Step("Short Desc 01", "description", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", null));
//            add(new Step("Short Desc 02", "description", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4", null));
//            add(new Step("Short Desc 03", "description", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4", null));
//            add(new Step("Short Desc 04", "description", "URL", null));
//        }};
//    }
}
