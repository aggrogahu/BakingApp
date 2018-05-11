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

    private static final String RECIPE = "recipeKey";

    public interface OnStepClickListener{
        void onStepSelected(int i, Recipe recipe);
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

        //read data from recipe array
        //load saved instancestate
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            assert args != null;
            mRecipe = (Recipe) args.get("recipe");
        } else {
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        List<Object> mDetailList = buildRecipeObjectList();

        mRecyclerView.setAdapter(new RecipeDetailAdapter(mDetailList, getContext(), mCallback, mRecipe));

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
    }

    //    private ArrayList<Object> getDummyArrayList(){
//        ArrayList<Object> detailsList = new ArrayList<>();
//        detailsList.add("Nutella Pie");
//        detailsList.add(getString(R.string.ingredients_label));
//        detailsList.add(new Ingredient("12 tblsp", "sugar"));
//        detailsList.add(new Ingredient("2 cup", "Graham Cracker crumbs"));
//        detailsList.add(new Ingredient("6 tblsp", "Nutella or other chocolate-hazelnut spread ofc"));
//        detailsList.add(getString(R.string.steps_label));
//        detailsList.add(new Step(
//                "Recipe Introduction",
//                "Recipe Introduction",
//                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4"
//                ,
//                null));
//        detailsList.add(new Step(
//                "Starting prep",
//                "1. Preheat the oven to 350°F. Butter a 9\" deep dish pie pan.",
//                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4",
//                null));
//        detailsList.add(new Step(
//                "Press shit",
//                "Press it",
//                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4",
//                null));
//        return detailsList;
//    }
}
