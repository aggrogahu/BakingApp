package leonard.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.data.Ingredient;
import leonard.bakingapp.data.Step;

public class RecipeDetailFragment extends Fragment {

    OnStepClickListener mCallback;

    public interface OnStepClickListener{
        void onStepSelected(int position);
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

        List<Object> mDetailList = getDummyArrayList();

        mRecyclerView.setAdapter(new RecipeDetailAdapter(mDetailList, getContext()));

        return rootView;
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
                "Starting prep",
                "1. Preheat the oven to 350°F. Butter a 9\" deep dish pie pan.",
                null,
                null));
        return detailsList;
    }
}