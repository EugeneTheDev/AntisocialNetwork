package com.styleru.eugene.antisocialnetwork;

import android.app.Application;

public class AntisocialNetworkApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
       appComponent = DaggerAppComponent.builder()
                .context(this)
                .build();
        super.onCreate();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

}
