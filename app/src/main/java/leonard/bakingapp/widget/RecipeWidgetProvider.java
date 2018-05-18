package leonard.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.widget.RemoteViews;

import leonard.bakingapp.R;
import leonard.bakingapp.RecipeDetailActivity;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
        RemoteViews rv = getRecipeViews(context, appWidgetId);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        Debug.waitForDebugger();
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private static RemoteViews getRecipeViews(Context context, int id){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

        // Set RecipeWdgetService as adapter
//        Intent intent = new Intent(context, RecipeWidgetService.class);
//        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
//        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
//        views.setRemoteAdapter(R.id.recipe_view_flipper,intent);
//
//        // Set the PlantDetailActivity intent to launch when clicked
//        Intent appIntent = new Intent(context, RecipeDetailActivity.class);
//        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setPendingIntentTemplate(R.id.recipe_view_flipper, appPendingIntent);


//        views.setEmptyView(R.id.recipe_view_flipper,R.id.empty_view);
        // dummy information
        RemoteViews test1 = new RemoteViews(context.getPackageName(),R.layout.step_short_desc);
        RemoteViews test2 = new RemoteViews(context.getPackageName(),R.layout.step_short_desc);
        views.addView(R.id.recipe_view_flipper,test1);
        test2.setTextViewText(R.id.short_desc_text_view, "derp");
        views.addView(R.id.recipe_view_flipper,test2);
//        Intent intent =

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
}

