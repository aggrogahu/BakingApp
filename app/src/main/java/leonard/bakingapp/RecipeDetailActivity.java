package leonard.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.data.Ingredient;
import leonard.bakingapp.data.Step;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener{
//    private RecyclerView.Adapter mAdapter;


    //TODO refactor code to fragment
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


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

    }
}
