package leonard.bakingapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import leonard.bakingapp.classes.Ingredient;
import leonard.bakingapp.classes.Recipe;
import leonard.bakingapp.classes.Step;

import static leonard.bakingapp.RecipeDetailFragment.SELECTED;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener{
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Log.d(TAG, "onCreate");

        int selected = RecyclerView.NO_POSITION;

        // Determine if you're creating a two-pane or single-pane display
        if(findViewById(R.id.step_detail_container) != null) {
            mTwoPane = true;
            Recipe recipe = getIntent().getParcelableExtra("recipe");
            selected = recipe.ingredients.length + 3;

            // Initialize the step detail with first step if two pane
            if (savedInstanceState == null){
                displayStep(0);
            }

        } else {
            mTwoPane = false;
        }


        if (savedInstanceState == null){
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            Bundle b = getIntent().getExtras();
            b.putInt(SELECTED, selected);
            recipeDetailFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, recipeDetailFragment)
                    .commit();
        }

    }

    /**
     * implement the interface that acts as an on click listener for the recipe detail recyclerview
     * @param position  position of the items that was clicked
     * @param recipe  the recipe used to launch the step detail activity or display fragment
     * @return  true if this is a two pane tablet layout; false means this is a single fragment display
     */
    @Override
    public boolean onStepSelected(int position, Recipe recipe) {
        //figure out offset to get position of recipe steps in the recycler view
        int offsetPosition = position - (recipe.ingredients.length + 3);

        //launch step detail; display step detail fragment if two pane layout
        if(!mTwoPane) {
            // build intent
            final Intent intent = new Intent(this, StepsActivity.class);
            Bundle b = new Bundle();
            b.putInt("selectedStep", offsetPosition);

            // build step array from object list
            ArrayList<Step> stepList = new ArrayList<Step>();
            stepList.addAll(Arrays.asList(recipe.steps));

            b.putParcelableArrayList("stepList", stepList);
            intent.putExtras(b);
            this.startActivity(intent);
        } else {
            // two panel display
            displayStep(offsetPosition);
        }
        return mTwoPane;
    }

    /**
     *  display the step detail fragment in the second pane
     * @param position index number of the step to display
     */
    private void displayStep(int position){
        StepsFragment stepsFragment = new StepsFragment();
        Bundle arg = new Bundle();
        Recipe recipe = getIntent().getParcelableExtra("recipe");
        arg.putParcelable(StepsFragment.STEP, recipe.steps[position]);
        arg.putBoolean("areNotDelaying", true); // functionality not yet implemented
        stepsFragment.setArguments(arg);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_detail_container, stepsFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
