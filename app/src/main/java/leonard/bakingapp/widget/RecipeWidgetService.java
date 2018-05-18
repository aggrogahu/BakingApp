package leonard.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Debug;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;

import leonard.bakingapp.R;
import leonard.bakingapp.classes.Ingredient;
import leonard.bakingapp.data.RecipeTable;

public class RecipeWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(this.getApplicationContext());
    }
}

class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final String TAG = RecipeRemoteViewsFactory.class.getSimpleName();
    Context mContext;
    Cursor mCursor;
    Ingredient[] ingredients;
//        private int mInstanceId = AppWidgetManager.INVALID_APPWIDGET_ID;

    public RecipeRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
//            mInstanceId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (mCursor != null) mCursor.close();
        mCursor = mContext.getContentResolver().query(
                RecipeTable.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        mCursor.moveToPosition(1);
        Gson gson = new Gson();
        String ingredientJson = mCursor.getString(mCursor.getColumnIndex("col_ingredients"));
        ingredients = gson.fromJson(ingredientJson, Ingredient[].class);

    }

    @Override
    public void onDestroy() {
//        mCursor.close();
    }

    @Override
    public int getCount() {
        if (mCursor == null) return 0;
        return ingredients.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (mCursor == null || mCursor.getCount() == 0) {
            Log.d(TAG, "getViewAt: null " );
            return null;
        }
        mCursor.moveToPosition(position);
        Ingredient ingredient = ingredients[position];
        RemoteViews ingredientRv = new RemoteViews(mContext.getPackageName(), R.layout.widget_ingredient);
//        ingredientRv.setTextViewText(R.id.short_desc_text_view,ingredient.ingredient);
        String m = ingredient.measure.toLowerCase();
        if (m.equals("unit")) {
            m = "";
        }
        ingredientRv.setTextViewText(R.id.widget_ingredient_text_view, ingredient.ingredient);
        ingredientRv.setTextViewText(R.id.widget_measure_text_view, ingredient.quantity + " " + m);
        return ingredientRv;
//        return null;
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
