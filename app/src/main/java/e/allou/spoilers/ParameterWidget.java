package e.allou.spoilers;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class ParameterWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.parameter_widget);
        views.setTextViewText(R.id.appwidget_text, "HELLO");

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.parameter_widget);
            views.setTextViewText(R.id.appwidget_text,"HELLO YOU");
            Intent intent = new Intent(context, ParameterWidget.class);
            Intent intentParametre = new Intent(context,Parametre.class);
            PendingIntent pendingIntentParametre = PendingIntent.getActivity(context, 0, intentParametre, 0);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);

            //TODO : SI LOGOUT, Lancer le Menu + POP UP vous n'etes pas connecté

            PendingIntent pendingIntent  = PendingIntent.getBroadcast(context,0,
                    intent,PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.buttonAccesParameter, pendingIntentParametre);
            updateAppWidget(context, appWidgetManager, appWidgetId);
            appWidgetManager.updateAppWidget(appWidgetId,views);
        }



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

