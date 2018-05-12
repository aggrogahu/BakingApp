package leonard.bakingapp;

import android.content.Context;
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
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leonard.bakingapp.classes.Recipe;

public class RecipeDetailFragment extends Fragment {
    private final String TAG = RecipeDetailFragment.class.getSimpleName();

    OnStepClickListener mCallback;
    private Recipe mRecipe;
    private RecipeDetailAdapter mRecipeDetailAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private static final String RECIPE = "recipeKey";
    static final String SELECTED = "selectedKey";
    private static final String CURRENT_SELECTED = "currentKey";
    private static final String LAYOUT_MANAGER = "layoutKey";

    public interface OnStepClickListener{
        boolean onStepSelected(int i, Recipe recipe);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }
    }

    public RecipeDetailFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recipe_detail_recycler_view);

        mRecyclerView.setHasFixedSize(true);



        mLinearLayoutManager = new LinearLayoutManager(getContext());
        //read data from recipe array
        //load saved instancestate
//        selected = RecyclerView.NO_POSITION;
        int selected;
        if (savedInstanceState == null) {

            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            Bundle args = getArguments();
            assert args != null;
            mRecipe = (Recipe) args.get("recipe");
            selected = args.getInt(SELECTED);


        } else {
            Log.d(TAG, "onCreateView: restoring");
            mLinearLayoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_MANAGER));
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
            selected = savedInstanceState.getInt(CURRENT_SELECTED);
        }
        List<Object> mDetailList = buildRecipeObjectList();
        mRecipeDetailAdapter = new RecipeDetailAdapter(mDetailList, getContext(), mCallback, mRecipe, selected);
        mRecyclerView.setAdapter(mRecipeDetailAdapter);



        return rootView;
    }

    private List<Object> buildRecipeObjectList() {
        ArrayList<Object> detailsList = new ArrayList<>();
        detailsList.add(mRecipe.name);
        detailsList.add(getString(R.string.ingredients_label));
        detailsList.addAll(Arrays.asList(mRecipe.ingredients));
        detailsList.add(getString(R.string.steps_label));
        detailsList.addAll(Arrays.asList(mRecipe.steps));
        return detailsList;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState!!!");
        outState.putParcelable(RECIPE, mRecipe);
        outState.putInt(CURRENT_SELECTED, mRecipeDetailAdapter.getSelectedPosition());
        outState.putParcelable(LAYOUT_MANAGER, mLinearLayoutManager.onSaveInstanceState());
    }

}
