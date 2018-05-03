package leonard.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.data.Step;

public class StepsActivity extends AppCompatActivity {
    private static final String TAG = StepsActivity.class.getSimpleName();
    //TODO might have to duplicate this code in recipe detail activity for two pane
    StepsPagerAdapter mStepsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        mStepsPagerAdapter = new StepsPagerAdapter(getSupportFragmentManager(), getDummyStepArrayList());
        mViewPager = findViewById(R.id.step_pager);
        mViewPager.setAdapter(mStepsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tick_marks);
        tabLayout.setupWithViewPager(mViewPager);
        int position = getIntent().getIntExtra("selectedStep", 0);
        Log.d(TAG, "position: " + position);
        mViewPager.setCurrentItem(position);
    }

    private List<Step> getDummyStepArrayList(){
        return new ArrayList<Step>(){{
            add(new Step("Short Desc 01", "description", "URL", null));
            add(new Step("Short Desc 02", "description", "URL", null));
            add(new Step("Short Desc 03", "description", "URL", null));
            add(new Step("Short Desc 04", "description", "URL", null));
        }};
    }
}
