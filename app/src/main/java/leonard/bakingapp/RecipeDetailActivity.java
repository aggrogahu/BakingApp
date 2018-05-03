package leonard.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.data.Ingredient;
import leonard.bakingapp.data.Step;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener{
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
//    private RecyclerView.Adapter mAdapter;


    //COMPLETED refactor code to fragment
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

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
    public void onStepSelected(int position) {
//TODO launch step detail; display step detail fragment if two pane layout
        final Intent intent = new Intent(this,StepsActivity.class);
        Bundle b = new Bundle();
        //TODO figure out correct offset programmatically
        b.putInt("selectedStep", position-6);
        intent.putExtras(b);
        this.startActivity(intent);
    }
}
