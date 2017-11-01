package com.manoj.sampleapp.navigator;

import android.content.Context;
import android.content.Intent;

import com.manoj.sampleapp.activity.ScenarioOneActivity;
import com.manoj.sampleapp.activity.ScenarioTwoActivity;

/**
 * Created by manoj on 1/11/17.
 */

public final class ActivityNavigator {

    public static void navigateToScenarioOne(Context context) {
        if (context != null) {
            Intent intentToLaunch = ScenarioOneActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public static void navigateToScenarioTwo(Context context) {
        if (context != null) {
            Intent intentToLaunch = ScenarioTwoActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
