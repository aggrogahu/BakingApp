package leonard.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Debug;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import leonard.bakingapp.R;
import leonard.bakingapp.data.RecipeTable;

public class RecipeWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    private class RecipeRemoteViewsFactory implements RemoteViewsFactory {

        private final String TAG = RecipeRemoteViewsFactory.class.getSimpleName();
        Context mContext;
        Cursor mCursor;
        private int mInstanceId = AppWidgetManager.INVALID_APPWIDGET_ID;

        public RecipeRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
            mInstanceId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

            Log.d(TAG, "onDataSetChanged: " + mCursor);
            if (mCursor != null) mCursor.close();
            mCursor = mContext.getContentResolver().query(
                    RecipeTable.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
            Log.d(TAG, "onDataSetChanged: " + mCursor.getCount());

        }

        @Override
        public void onDestroy() {
            mCursor.close();
        }

        @Override
        public int getCount() {
            if (mCursor == null) return 0;
            return mCursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (mCursor == null || mCursor.getCount() == 0) {
                Log.d(TAG, "getViewAt: null " );
                return null;
            }
            mCursor.moveToPosition(position);
            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.step_short_desc);
            rv.setTextViewText(R.id.short_desc_text_view,"position: " + position);
            return rv;
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
}
