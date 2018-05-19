package leonard.bakingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import leonard.bakingapp.classes.Step;

class StepsPagerAdapter extends FragmentStatePagerAdapter{
    private final String TAG = StepsAdapter.class.getSimpleName();
    private List<Step> mStepList;

    public StepsPagerAdapter(FragmentManager fm, List<Step> stepList) {
        super(fm);
        mStepList = stepList;
    }

    @Override
    public Fragment getItem(int position) {
        // initialize and return step fragment
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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Log.d(TAG, "destroyItem!!!");
    }

}
