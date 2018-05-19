package leonard.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.R;
import leonard.bakingapp.data.RecipeTable;

public class RecipeWidgetConfigure extends AppCompatActivity {
    private static final String RECIPE_INDEX_KEY = "recipe_index";
    private static final String PREFS_NAME = "leonard.bakingapp.widget.RecipeWidgetProvider";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    ListView mListView;

    public RecipeWidgetConfigure(){
        super();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);
        setContentView(R.layout.appwidget_config);

        // get id from intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        mListView = findViewById(R.id.config_list_view);
        Cursor c = getContentResolver().query(
                RecipeTable.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        List<String> recipes = new ArrayList<>();
        if (c != null) {
            for (int i = 0; i < c.getCount(); i++){
                c.moveToPosition(i);
                String recipeName = c.getString(c.getColumnIndex("col_recipe"));
                recipes.add(recipeName);
            }
        }

        mListView.setAdapter(new ArrayAdapter<String>(this,R.layout.step_short_desc,recipes));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Context context = RecipeWidgetConfigure.this;

                // When the button is clicked, save the string in our prefs and return that they
                // clicked OK.
//                String titlePrefix = mAppWidgetPrefix.getText().toString();
                saveRecipe(context, mAppWidgetId, position);

                // Push widget update to surface with newly set prefix
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RecipeWidgetProvider.updateAppWidget(context, appWidgetManager,
                        mAppWidgetId, position);
                // Make sure we pass back the original appWidgetId
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
            }
        });



    }

    static void saveRecipe(Context context, int appWidgetId, int index) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putInt(RECIPE_INDEX_KEY + appWidgetId, index);
        prefs.commit();
    }

    static int loadPrefRecipeIndex(Context context, int appWidgetId){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(RECIPE_INDEX_KEY + appWidgetId, 0);
    }
}
