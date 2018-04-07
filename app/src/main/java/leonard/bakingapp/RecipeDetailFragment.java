package leonard.bakingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecipeDetailFragment extends Fragment {
    private String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////        mRecyclerView = findViewById(R.id.recipe_recycler_view);
//
//        mRecyclerView.setHasFixedSize(true);
//
//        int orientation = getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
////            mLayoutManager = new GridLayoutManager(this, 2);
//        } else {
////            mLayoutManager = new LinearLayoutManager(this);
//        }
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new RecipeAdapter(null);
//        mRecyclerView.setAdapter(mAdapter);
//
//    }
}
