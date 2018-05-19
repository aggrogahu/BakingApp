package leonard.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Debug;
import android.widget.RemoteViews;

import com.google.gson.Gson;

import leonard.bakingapp.R;
import leonard.bakingapp.RecipeDetailActivity;
import leonard.bakingapp.classes.Ingredient;
import leonard.bakingapp.data.RecipeTable;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {
    public static final String NEXT_ACTION = "leonard.bakingapp.action.show_next";
    public static final String PREVIOUS_ACTION = "leonard.bakingapp.action.show_previous";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, int index) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
        RemoteViews rv = getRecipeViews(context, index, appWidgetId);

        // Instruct the widget manager to update the widget
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list_view);
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        Debug.waitForDebugger();
        for (int appWidgetId : appWidgetIds) {
            int mIndex = RecipeWidgetConfigure.loadPrefRecipeIndex(context, appWidgetId);
            updateAppWidget(context, appWidgetManager, appWidgetId, mIndex);
        }
    }

    private static RemoteViews getRecipeViews(Context context, int index, int appWidgetId){
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_page);

        // Set RecipeWidgetService as adapter
//        Intent intent = new Intent(context, RecipeWidgetService.class);
//        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
//        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
//        views.setRemoteAdapter(R.id.recipe_view_flipper,intent);
//
//        // Set the PlantDetailActivity intent to launch when clicked
//        Intent appIntent = new Intent(context, RecipeDetailActivity.class);
//        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setPendingIntentTemplate(R.id.recipe_view_flipper, appPendingIntent);

//        // Bind the click intent for the next button on the widget
//        final Intent nextIntent = new Intent(context,
//                RecipeWidgetProvider.class);
//        nextIntent.setAction(RecipeWidgetProvider.NEXT_ACTION);
//        nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
//        final PendingIntent nextPendingIntent = PendingIntent
//                .getBroadcast(context, 0, nextIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.widget_next_button, nextPendingIntent);
//
//        // Bind the click intent for the next button on the widget
//        final Intent prevIntent = new Intent(context,
//                RecipeWidgetProvider.class);
//        prevIntent.setAction(RecipeWidgetProvider.PREVIOUS_ACTION);
//        prevIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
//        final PendingIntent prevPendingIntent = PendingIntent
//                .getBroadcast(context, 0, prevIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.widget_prev_button, prevPendingIntent);

        // get cursor
        Cursor mCursor = context.getContentResolver().query(
                RecipeTable.CONTENT_URI,
                null,
                null,
                null,
                null
        );//content://recipe_provider.authority/recipe

//        for (int i = 0; i < mCursor.getCount(); i++){
            // retrieve recipe
            mCursor.moveToPosition(index);
            String recipeName = mCursor.getString(mCursor.getColumnIndex("col_recipe"));

            // convert json to Ingredient array
//            Gson gson = new Gson();
//            String ingredientJson = mCursor.getString(mCursor.getColumnIndex("col_ingredients"));
//            Ingredient[] ingredients = gson.fromJson(ingredientJson, Ingredient[].class);

            views.setTextViewText(R.id.widget_recipe_name, recipeName);
            // Set the RecipeWidgetService intent to act as the adapter for the ListView
            Intent intent = new Intent(context, RecipeWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            views.setRemoteAdapter(R.id.widget_list_view, intent);

            // place contents into page
//            RemoteViews remoteViewPage = new RemoteViews(context.getPackageName(), R.layout.widget_page);
//            remoteViewPage.setTextViewText(R.id.widget_recipe_name, recipeName);
//
//            // Set the RecipeWidgetService intent to act as the adapter for the ListView
//            Intent intent = new Intent(context, RecipeWidgetService.class);
//            remoteViewPage.setRemoteAdapter(R.id.widget_list_view, intent);



//        RemoteViews test = new RemoteViews(context.getPackageName(), R.layout.config_text_layout);
//        test.setTextViewText(R.id.short_desc_text_view,"position:");
//        remoteViewPage.addView(R.id.widget_page_linear_layout,test);

            // brute force adding to linearlayout // delete this code once list view is working
//            for (Ingredient ingredient : ingredients) {
//                remoteViewPage.addView(R.id.widget_page_linear_layout, addIngredient(context, ingredient));
//            }



//            views.addView(R.id.recipe_view_flipper,remoteViewPage);
//        }



//        views.setEmptyView(R.id.recipe_view_flipper,R.id.empty_view);
        // dummy information
//        RemoteViews test1 = new RemoteViews(context.getPackageName(),R.layout.config_text_layout);
//        RemoteViews test2 = new RemoteViews(context.getPackageName(),R.layout.config_text_layout);
//        test1.setTextViewText(R.id.short_desc_text_view, "getCount: " + cerp);
//        views.addView(R.id.recipe_view_flipper,test1);
//        test2.setTextViewText(R.id.short_desc_text_view, "getCount: " + mCursor.getCount());
//        views.addView(R.id.recipe_view_flipper,test2);
//        Intent intent =

        return views;
    }

    private static RemoteViews addIngredient(Context context, Ingredient ingredient){
        RemoteViews ingredientRv = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient);
        String m = ingredient.measure.toLowerCase();
        if (m.equals("unit")) {
            m = "";
        }
        ingredientRv.setTextViewText(R.id.widget_ingredient_text_view, ingredient.ingredient);
        ingredientRv.setTextViewText(R.id.widget_measure_text_view, ingredient.quantity + " " + m);
        return ingredientRv;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

//    @Override
//    public void onReceive(Context context, Intent intent) {
//        super.onReceive(context, intent);
//        final String action = intent.getAction();
//        if (action.equals(NEXT_ACTION)) {
//            RemoteViews rv = new RemoteViews(context.getPackageName(),
//                    R.layout.recipe_widget_provider);
//
//            rv.showNext(R.id.recipe_view_flipper);
//
//
//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context,RecipeWidgetProvider.class), rv);
//        }
//        if (action.equals(PREVIOUS_ACTION)) {
//            RemoteViews rv = new RemoteViews(context.getPackageName(),
//                    R.layout.recipe_widget_provider);
//
//            rv.showPrevious(R.id.recipe_view_flipper);
//
//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context,RecipeWidgetProvider.class), rv);
//        }
//    }

}

