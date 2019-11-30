package roomdemo.wiseass.com.roomdemo;

import android.app.Application;

import roomdemo.wiseass.com.roomdemo.dependencyinjection.ApplicationComponent;
import roomdemo.wiseass.com.roomdemo.dependencyinjection.ApplicationModule;
import roomdemo.wiseass.com.roomdemo.dependencyinjection.DaggerApplicationComponent;
import roomdemo.wiseass.com.roomdemo.dependencyinjection.RoomModule;

/**
 * DispatchingAndroidInjector helps to inject Member Variables into Activities, Fragments, and so
 * forth.
 */

public class RoomDemoApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
