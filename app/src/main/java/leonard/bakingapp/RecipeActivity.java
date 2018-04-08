package leonard.bakingapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    private String TAG = RecipeActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> mRecipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recipe_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        //TODO change to grid in case of wide/tablet screen size
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new LinearLayoutManager(this);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecipeList = new ArrayList<>();
        mRecipeList.add("Nutella Pie");
        mRecipeList.add("Brownies");
        mRecipeList.add("Yellow Cake");
        mRecipeList.add("Cheesecake");

        mAdapter = new RecipeAdapter(mRecipeList,this);
        mRecyclerView.setAdapter(mAdapter);

    }
}
