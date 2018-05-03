package leonard.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import leonard.bakingapp.data.Step;

public class StepsFragment extends Fragment {

    public static final String STEP = "step";
//    public static final String LIST_INDEX = "list_index";
    public static final String ARG_OBJECT = "object";

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

        //TODO set on click listener

        View rootView = inflater.inflate(R.layout.fragment_recipe_steps,container,false);
        Bundle args = getArguments();
        Step mStep = args.getParcelable(STEP);

        ((TextView) rootView.findViewById(R.id.text1)).setText(
                "Step " +
                Integer.toString(args.getInt(ARG_OBJECT)) +
        " " + mStep.shortDes);
        return rootView;
    }

//    public void setStepList(List<Integer> steps){ mStepList = steps;}
//
//    public void setListIndex(int index){mListIndex = index;}

//    private void initializeMediaSession(){}
//
//    private void initializeExoPlayer(){}
}
