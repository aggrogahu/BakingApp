package leonard.bakingapp.widget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

import leonard.bakingapp.R;
import leonard.bakingapp.database.RecipeTable;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {
//    public static final String NEXT_ACTION = "leonard.bakingapp.action.show_next";
//    public static final String PREVIOUS_ACTION = "leonard.bakingapp.action.show_previous";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, int index) {

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
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_page);

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
        );

        // retrieve recipe
        mCursor.moveToPosition(index);
        String recipeName = mCursor.getString(mCursor.getColumnIndex("col_recipe"));

        views.setTextViewText(R.id.widget_recipe_name, recipeName);
        // Set the RecipeWidgetService intent to act as the adapter for the ListView
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        views.setRemoteAdapter(R.id.widget_list_view, intent);

        return views;
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

