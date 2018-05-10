package leonard.bakingapp;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import leonard.bakingapp.data.Recipe;

public class RecipeActivity extends AppCompatActivity {
    private String TAG = RecipeActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mRecipeList;
//    private String recipeJsonStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recipe_recycler_view);

//        recipeJsonStr = "";

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

//        try {
//            JSONObject recipeJson = new JSONObject(recipeJsonStr);
//            JSONArray recipeJsonArray = recipeJson.getJSONArray("recipes");
//            int jsonLength = recipeJsonArray.length();
//            for(int i = 0; i < jsonLength; i++){
//                mRecipeList.add(recipeJsonArray.getJSONObject(i).getString("name"));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        mRecipeList.add("Nutella Pie");
//        mRecipeList.add("Brownies");
//        mRecipeList.add("Yellow Cake");
//        mRecipeList.add("Cheesecake");

        mAdapter = new RecipeAdapter(mRecipeList,this);
        mRecyclerView.setAdapter(mAdapter);
        FetchRecipesTask recipesTask = new FetchRecipesTask();
        recipesTask.execute();

    }

    private List<String> extractRecipeNames(String recipeStr //JSONObject[] recipeJsonArray
    ){
        Log.d(TAG, "extractRecipeNames");
        List<String> recipeList = new ArrayList<>();
        try {
            JSONArray recipeJson = new JSONArray(recipeStr);
//            JSONArray recipeJsonArray = recipe;//jsonObject.getJSONArray("recipes");

            int jsonLength = recipeJson.length();//recipeJsonArray.length;//.length();
//            Log.d(TAG, "extractRecipeNames: jsonlength = " + jsonLength);
            for(int i = 0; i < jsonLength; i++){
//                Log.d(TAG, "adding: " + recipeJsonArray[i].getString("name"));
//
                String str = recipeJson.getJSONObject(i).getString("name");
                recipeList.add(str);
            }
            Gson gson = new Gson();
            Recipe[] recipes = gson.fromJson(recipeStr,Recipe[].class);
            Recipe recipe = recipes[0];
            Log.d(TAG, "extractRecipeNames: " + recipe.name);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    public class FetchRecipesTask extends AsyncTask<String,Void,List<String>>{
        @Override
        protected List<String> doInBackground(String... strings) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String recipeJsonStr;
            JSONObject[] jsonArray = null;

            try {
                Log.d(TAG, "doInBackground: (1) trying");
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("http")
                        .authority("d17h27t6h515a5.cloudfront.net")
                        .appendPath("topher")
                        .appendPath("2017")
                        .appendPath("May")
                        .appendPath("59121517_baking")
                        .appendEncodedPath("baking.json");
                URL url = new URL(builder.build().toString());

                Log.d(TAG, "doInBackground: (2) connecting");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();

//                JSONParser jsonParser = new JSONParser();
//                jsonArray = (JSONArray) jsonParser.parse(
//                        new InputStreamReader(inputStream, "UTF-8"));


                StringBuffer stringBuffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }

                Log.d(TAG, "doInBackground: (3) buffering");
                reader = new BufferedReader(new InputStreamReader(inputStream));
//                Gson gson = new Gson();
////                Type collectionType = new TypeToken<Collection<JSONObject>>(){}.getType();
////                jsonArray = gson.fromJson(reader, collectionType);
//                jsonArray = gson.fromJson(reader,JSONObject[].class);
//                Log.d(TAG, "doInBackground: " + jsonArray);
////                Recipe recipe = gson.fromJson(jsonArray[0],Recipe.class);
//                //TODO(1) figure out how to parse this
                Log.d(TAG, "doInBackground: (4) lining");

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
//                    Log.d(TAG, "doInBackground: building");
                    stringBuffer.append(line + "\n");
                }
                Log.d(TAG, "doInBackground: (5) lined");

//                if (stringBuffer.length() == 0) {
//                    // Stream was empty.  No point in parsing.
//                    return null;
//                }
                recipeJsonStr = //gson.fromJson(reader,String.class);
                stringBuffer.toString();
                Log.d(TAG, "doInBackground: stringed");
            } catch (IOException e) {
                Log.d(TAG, "doInBackground: IOexception");
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
//            return null;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            updateRecipeCards(strings);
        }
    }

    private void updateRecipeCards(List<String> recipes){
        if (recipes != null) {
            mRecipeList.addAll(recipes);
        }
        mAdapter.notifyDataSetChanged();
    }


}
