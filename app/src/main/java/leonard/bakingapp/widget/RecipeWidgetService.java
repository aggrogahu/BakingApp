package leonard.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;

import leonard.bakingapp.R;
import leonard.bakingapp.classes.Ingredient;
import leonard.bakingapp.database.RecipeTable;

public class RecipeWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

//    private final String TAG = RecipeRemoteViewsFactory.class.getSimpleName();
    private Context mContext;
    private Cursor mCursor;
    private Ingredient[] ingredients;
        private int mInstanceId;

    public RecipeRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
            mInstanceId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        // get cursor
        if (mCursor != null) mCursor.close();
        mCursor = mContext.getContentResolver().query(
                RecipeTable.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        if (mInstanceId >= 0){
            mCursor.moveToPosition(RecipeWidgetConfigure.loadPrefRecipeIndex(mContext,mInstanceId));
        }
        // get ingredients json from the cursor and convert to array
        Gson gson = new Gson();
        String ingredientJson = mCursor.getString(mCursor.getColumnIndex("col_ingredients"));
        ingredients = gson.fromJson(ingredientJson, Ingredient[].class);

    }

    @Override
    public void onDestroy() {
        mCursor.close();
    }

    @Override
    public int getCount() {
        if (mCursor == null) return 0;
        return ingredients.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // return a remote view with an ingredient in it
        if (mCursor == null || mCursor.getCount() == 0) {
            return null;
        }
        mCursor.moveToPosition(position);
        Ingredient ingredient = ingredients[position];
        String m = ingredient.measure.toLowerCase();
        if (m.equals("unit")) {
            m = "";
        }
        String measure = ingredient.quantity + " " + m;
        String ingredientString = ingredient.ingredient;

            RemoteViews ingredientRv = new RemoteViews(mContext.getPackageName(), R.layout.widget_ingredient);

            ingredientRv.setTextViewText(R.id.widget_ingredient_text_view, ingredientString);
            ingredientRv.setTextViewText(R.id.widget_measure_text_view,measure);
            return ingredientRv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
