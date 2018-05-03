package leonard.bakingapp;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.data.Step;

class StepsPagerAdapter extends FragmentStatePagerAdapter{
    List<Step> mStepList;

    public StepsPagerAdapter(FragmentManager fm, List<Step> stepList) {
        super(fm);
        mStepList = stepList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new StepsFragment();
        Bundle args = new Bundle();
        args.putInt(StepsFragment.ARG_OBJECT, position + 1);
        args.putParcelable(StepsFragment.STEP, mStepList.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return mStepList.size();
    }
}
