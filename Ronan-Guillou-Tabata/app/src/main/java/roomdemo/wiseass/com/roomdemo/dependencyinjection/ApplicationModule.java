package roomdemo.wiseass.com.roomdemo.dependencyinjection;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import roomdemo.wiseass.com.roomdemo.RoomDemoApplication;

@Module
public class ApplicationModule {
    private final RoomDemoApplication application;
    public ApplicationModule(RoomDemoApplication application) {
        this.application = application;
    }

    @Provides
    RoomDemoApplication provideRoomDemoApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }
}
