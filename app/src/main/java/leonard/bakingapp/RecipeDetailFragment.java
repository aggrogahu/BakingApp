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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leonard.bakingapp.classes.Recipe;

public class RecipeDetailFragment extends Fragment {
    private final String TAG = RecipeDetailFragment.class.getSimpleName();

    OnStepClickListener mCallback;
    private Recipe mRecipe;
    private RecipeDetailAdapter mRecipeDetailAdapter;
    private int selected;

    private static final String RECIPE = "recipeKey";
    static final String SELECTED = "selectedKey";
    private static final String CURRENT_SELECTED = "currentKey";

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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        TODO save and restore layout manager state

        //read data from recipe array
        //load saved instancestate
        selected = RecyclerView.NO_POSITION;
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            assert args != null;
            mRecipe = (Recipe) args.get("recipe");
            selected = args.getInt(SELECTED);
        } else {
            Log.d(TAG, "onCreateView: restoring");
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
        outState.putInt(CURRENT_SELECTED, selected);
    }

}
