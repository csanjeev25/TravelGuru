package com.insomniac.travelguru;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import net.danlew.android.joda.JodaTimeAndroid;
import android.os.StrictMode;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;
import timber.log.Timber;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this))
            //performing leak analysis
            return;

        enabledStrictMode();
        LeakCanary.install(this);
        Timber.plant(new Timber.DebugTree());
        JodaTimeAndroid.init(this);
    }

    private void enabledStrictMode(){
        if(SDK_INT >= GINGERBREAD){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyDeath()
            .build());
        }
    }
}

