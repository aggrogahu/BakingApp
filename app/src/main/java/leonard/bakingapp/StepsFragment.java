package leonard.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StepsFragment extends Fragment {

    public static final String STEPS_LIST = "steps_list";
    public static final String LIST_INDEX = "list_index";

    private List<Integer> mStepList;
    private int mListIndex;

    public StepsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //TODO StepFragment load saved instance

        //TODO Inflate layout and set views accordingly (send dummy information to recyclerView adapter)

        //TODO set on click listener

        return inflater.inflate(R.layout.fragment_recipe_steps,container,false);
    }

    public void setStepList(List<Integer> steps){ mStepList = steps;}

    public void setListIndex(int index){mListIndex = index;}

//    private void initializeMediaSession(){}
//
//    private void initializeExoPlayer(){}
}
