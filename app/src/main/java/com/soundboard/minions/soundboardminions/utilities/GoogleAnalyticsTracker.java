package com.soundboard.minions.soundboardminions.utilities;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

/**
 * Google Analytics Tracker Instance
 */
public final class GoogleAnalyticsTracker {

    public static void trackNavigation(final Context context, final String activityName) {
        GoogleAnalytics.getInstance(context).getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
        final Tracker mTracker = TrackerManagerService.getTracker(context);
        mTracker.setScreenName(activityName);
        mTracker.send(new HitBuilders.AppViewBuilder().build());
    }

    public static void trackAction(final Context context, final String activityName, final String action) {
        GoogleAnalytics.getInstance(context).getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
        final Tracker mTracker = TrackerManagerService.getTracker(context);
        mTracker.setScreenName(activityName);
        mTracker.send(new HitBuilders.EventBuilder().setAction(action).build());
    }
}