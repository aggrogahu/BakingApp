package leonard.bakingapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate:)");
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recipe_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        //TODO change to grid in case of wide/tablet screen size
        int orientaion = getResources().getConfiguration().orientation;
        if (orientaion == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new LinearLayoutManager(this);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecipeAdapter(null);
        mRecyclerView.setAdapter(mAdapter);

    }
}
