package leonard.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import leonard.bakingapp.classes.Recipe;
import leonard.bakingapp.classes.Step;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener{
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
//    private RecyclerView.Adapter mAdapter;


    //COMPLETED refactor code to fragment
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

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

// TODO Initialize the step detail with first step if two pane
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
    public void onStepSelected(int position, Recipe recipe) {

        //TODO launch step detail; display step detail fragment if two pane layout
        final Intent intent = new Intent(this,StepsActivity.class);
        Bundle b = new Bundle();
        //figure out correct offset programmatically
        int offset = recipe.ingredients.length + 3;
        b.putInt("selectedStep", position - offset);

        //build step array from object list
        ArrayList<Step> stepList = new ArrayList<Step>();
        int numSteps = recipe.steps.length;
        for (int i = 0; i < numSteps; i++) {
            stepList.add(recipe.steps[i]);
//            stepList.add((Step)list.get(i));
        }

        b.putParcelableArrayList("stepList", stepList);
        intent.putExtras(b);
        this.startActivity(intent);
    }
}
