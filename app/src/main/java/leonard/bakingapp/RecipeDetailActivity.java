package leonard.bakingapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import leonard.bakingapp.classes.Recipe;
import leonard.bakingapp.classes.Step;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener{
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
    private boolean mTwoPane;
//    private RecyclerView.Adapter mAdapter;


    //COMPLETED refactor code to fragment
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Log.d(TAG, "onCreate");

        // Determine if you're creating a two-pane or single-pane display
        if(findViewById(R.id.step_detail_container) != null) {
            mTwoPane = true;

            // TODO Initialize the step detail with first step if two pane
            if (savedInstanceState == null){
                displayStep(0);

            } else {
                mTwoPane = false;
            }

        }
        if (savedInstanceState == null){
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            recipeDetailFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, recipeDetailFragment)
                    .commit();
        } else {
//            Log.d("M", "something saved");
//            mFragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_STATE);
//            if (mFragment.isAdded()) {
//                return;
//            }else {
//                Log.d("M", "else");
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.main_container, mFragment)
//                        .commit();
//            }
        }

//        RecyclerView mRecyclerView = findViewById(R.id.recipe_detail_recycler_view);
//        mRecyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        List<Object> mDetailList = getDummyArrayList();
//
//        mRecyclerView.setAdapter(new RecipeDetailAdapter(mDetailList));

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onStepSelected(int position, Recipe recipe) {
        //figure out offset
        int offsetPosition = position - (recipe.ingredients.length + 3);

        //TODO launch step detail; display step detail fragment if two pane layout
        if(!mTwoPane) {
            Log.d(TAG, "onStepSelected: twopane");
            final Intent intent = new Intent(this, StepsActivity.class);
            Bundle b = new Bundle();

            b.putInt("selectedStep", offsetPosition);

            //build step array from object list
            ArrayList<Step> stepList = new ArrayList<Step>();
            stepList.addAll(Arrays.asList(recipe.steps));

            b.putParcelableArrayList("stepList", stepList);
            intent.putExtras(b);
            this.startActivity(intent);
        } else {
            Log.d(TAG, "onStepSelected: not");
            displayStep(offsetPosition);
        }
    }

    private void displayStep(int position){
        StepsFragment stepsFragment = new StepsFragment();
        Bundle arg = new Bundle();
        Recipe recipe = getIntent().getParcelableExtra("recipe");
        arg.putParcelable(StepsFragment.STEP, recipe.steps[position]);
        stepsFragment.setArguments(arg);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_detail_container, stepsFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBoolean("twopane", mTwoPane);
//        RecipeDetailFragment recipeDetailFragment = ()getFragmentManager().findFragmentById(R.id.recipe_detail_container);
//        getSupportFragmentManager().putFragment(outState, "myFragmentName", recipeDetailFragment);
    }
}
