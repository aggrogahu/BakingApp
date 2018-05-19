package leonard.bakingapp;

import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import leonard.bakingapp.classes.Recipe;
import leonard.bakingapp.database.RecipeTable;
//import leonard.bakingapp.data.RecipeTable;

public class RecipeActivity extends AppCompatActivity {
    private String TAG = RecipeActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Recipe> mRecipeArray;

    private static final String RECIPE_LIST = "recipeListKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recipe_recycler_view);

        //change to grid in case of wide/tablet screen size
        int orientation = getResources().getConfiguration().orientation;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels / displayMetrics.density;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE || width > 600) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new LinearLayoutManager(this);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecipeArray = new ArrayList<>();

        mAdapter = new RecipeAdapter(mRecipeArray,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        if (savedInstanceState == null){
            FetchRecipesTask recipesTask = new FetchRecipesTask();
            recipesTask.execute();
        } else {
            updateRecipeCards(savedInstanceState.<Recipe>getParcelableArrayList(RECIPE_LIST));
        }

                 /* "ctrl+/" on this line to turn on Stetho debugger
        final Context context = this;
        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                        .build());//*/


    }

    /**
     * Add recipes to database
     * @param mRecipeArray The array that contains recipes to add to database
     */
    private void addToRecipeDb(ArrayList<Recipe> mRecipeArray) {

        if(mRecipeArray!=null){
            for(int i = 0; i < mRecipeArray.size() ; i++)
                if (!checkIfExists(mRecipeArray.get(i))){
                    Gson gson = new Gson();
                    String ingredientJsonString = gson.toJson(mRecipeArray.get(i).ingredients);
                    String recipeName = mRecipeArray.get(i).name;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("col_recipe", recipeName);
                    contentValues.put("col_ingredients", ingredientJsonString);
                    getContentResolver().insert(RecipeTable.CONTENT_URI, contentValues);
                } else {
                    Log.d(TAG, "addToRecipeDb: recipe already exists");
                }


        }
    }

    /**
     * Check to see if a recipe is already in the database
     * @param recipe The recipe to be tested against the database
     * @return True if recipe already exists in database
     */
    private boolean checkIfExists(Recipe recipe) {
        //query to see if the recipe is already in the database
        Boolean exists = false;
        Cursor cursor = getContentResolver().query(RecipeTable.CONTENT_URI,null,"col_recipe=\"" + recipe.name + "\"",null,null);
        if(cursor.getCount()>0){
            exists = true;
        }
        cursor.close();
        return exists;
    }

    /**
     * takes recipes from a Json and converts it to a list
     * @param recipeJsonStr Json string that contains recipes that needs to be converted to List
     * @return recipes in a list
     */
    private List<Recipe> extractRecipeNames(String recipeJsonStr){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Recipe>>(){}.getType();
        List<Recipe> recipes = gson.fromJson(recipeJsonStr,collectionType);
        return recipes;
    }

    /**
     * async task to retrieve the recipe Json from the URL
     */
    public class FetchRecipesTask extends AsyncTask<String,Void,List<Recipe>>{
        @Override
        protected List<Recipe> doInBackground(String... strings) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String recipeJsonStr;

            try {
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("http")
                        .authority("d17h27t6h515a5.cloudfront.net")
                        .appendPath("topher")
                        .appendPath("2017")
                        .appendPath("May")
                        .appendPath("59121517_baking")
                        .appendEncodedPath("baking.json");
                URL url = new URL(builder.build().toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();


                StringBuffer stringBuffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }

                recipeJsonStr = stringBuffer.toString();
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: IOexception");
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }

            return extractRecipeNames(recipeJsonStr);
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);
            updateRecipeCards(recipes);
        }
    }

    /**
     *  update the recipe arrays, database, and recyclerView
     * @param recipes recipes to display and save
     */
    private void updateRecipeCards(List<Recipe> recipes){
        if (recipes != null) {
            mRecipeArray.clear();
            mRecipeArray.addAll(recipes);
        }
        addToRecipeDb(mRecipeArray);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE_LIST, mRecipeArray);
    }
}
