package leonard.bakingapp;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.data.Step;

class StepsPagerAdapter extends FragmentStatePagerAdapter{
    private final String TAG = StepsAdapter.class.getSimpleName();
    List<Step> mStepList;
//    private Fragment mCurrentFragment;

    //TODO save state of fragments
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

//    public Fragment getCurrentFragment() {
//        return mCurrentFragment;
//    }
//    //...
//    @Override
//    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        Log.d(TAG, "setPrimaryItem: ");
////        if (getCurrentFragment() != object) {
//            mCurrentFragment = ((Fragment) object);
////        }
//        super.setPrimaryItem(container, position, object);
//    }

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
