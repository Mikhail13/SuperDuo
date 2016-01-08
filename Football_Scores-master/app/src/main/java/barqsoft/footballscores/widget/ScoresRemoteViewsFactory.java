package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.ScoresAdapter;
import barqsoft.footballscores.Utilities;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ScoresRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private Context mContext;
    private int mAppWidgetId;
    private List<WidgetScore> mScores = new ArrayList();

    public ScoresRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        String today = DATE_FORMAT.format(new Date());
        String[] selectionArgs = new String[] {today};
        List<WidgetScore> newScores = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = mContext.getContentResolver().query(DatabaseContract.scores_table.buildScoreWithDate(),
                    null, null, selectionArgs, null);
            while (cursor.moveToNext()) {
                WidgetScore widgetScore = new WidgetScore(
                        cursor.getString(ScoresAdapter.COL_HOME),
                        cursor.getString(ScoresAdapter.COL_AWAY),
                        Utilities.getScores(cursor.getInt(ScoresAdapter.COL_HOME_GOALS),
                                cursor.getInt(ScoresAdapter.COL_AWAY_GOALS)));
                newScores.add(widgetScore);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.scores_appwidget);
        if (newScores.size() > 0) {
            mScores = newScores;
            remoteViews.setViewVisibility(R.id.widget_list_view, View.VISIBLE);
            remoteViews.setViewVisibility(R.id.no_data_text, View.GONE);
        } else {
            remoteViews.setViewVisibility(R.id.widget_list_view, View.GONE);
            remoteViews.setViewVisibility(R.id.no_data_text, View.VISIBLE);
        }
        appWidgetManager.updateAppWidget(mAppWidgetId, remoteViews);
    }

    @Override
    public void onDestroy() {
        mScores.clear();
    }

    @Override
    public int getCount() {
        return mScores.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.scores_appwidget_list_item);

        if (position <= getCount()) {
            WidgetScore score = mScores.get(position);

            rv.setTextViewText(R.id.home_team_name, score.getHomeTeamName());
            rv.setTextViewText(R.id.away_team_name, score.getAwayTeamName());
            rv.setTextViewText(R.id.score, score.getScore());
        }
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
