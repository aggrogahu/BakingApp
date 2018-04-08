package leonard.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import leonard.bakingapp.data.Ingredient;
import leonard.bakingapp.data.Step;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
    }
//    TODO build recipe detail screen using recyclerview and different view types
    private ArrayList<Object> getDummyArrayList(){
        ArrayList<Object> detailsList = new ArrayList<>();
        detailsList.add(getString(R.string.ingredients_label));
        detailsList.add(new Ingredient("12 tblsp", "sugar"));
        detailsList.add(getString(R.string.steps_label));
        detailsList.add(new Step(
                "Recipe Introduction",
                "Recipe Introduction",
                null,
                null));
        return detailsList;
    }
}
