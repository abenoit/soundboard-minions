package com.soundboard.minions.soundboardminions.utilities;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;


public final class TrackerManagerService {
    public static Tracker getTracker(Context context) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        return analytics.newTracker(Constants.APP_TRACKER);
    }
}
