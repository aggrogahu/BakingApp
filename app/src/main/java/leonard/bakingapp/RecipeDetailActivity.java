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

public class RecipeDetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Object> mDetailList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        mRecyclerView = findViewById(R.id.recipe_detail_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDetailList = getDummyArrayList();

        mRecyclerView.setAdapter(new RecipeDetailAdapter(mDetailList));

    }
//    TODO build recipe detail screen using recyclerview and different view types
    private ArrayList<Object> getDummyArrayList(){
        ArrayList<Object> detailsList = new ArrayList<>();
        detailsList.add("Nutella Pie");
        detailsList.add(getString(R.string.ingredients_label));
        detailsList.add(new Ingredient("12 tblsp", "sugar"));
        detailsList.add(new Ingredient("2 cup", "Graham Cracker crumbs"));
        detailsList.add(new Ingredient("6 tblsp", "Nutella or other chocolate-hazelnut spread ofc"));
        detailsList.add(getString(R.string.steps_label));
        detailsList.add(new Step(
                "Recipe Introduction",
                "Recipe Introduction",
                null,
                null));
        detailsList.add(new Step(
                "Recipe Introduction",
                "Recipe Introduction",
                null,
                null));
        return detailsList;
    }

}
