package roomdemo.wiseass.com.roomdemo.dependencyinjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import roomdemo.wiseass.com.roomdemo.create.CreateFragment;
import roomdemo.wiseass.com.roomdemo.detail.DetailFragment;
import roomdemo.wiseass.com.roomdemo.list.ListFragment;
import roomdemo.wiseass.com.roomdemo.tabata.TabataFragment;

/**
 * Annotated as a Singelton since we don't want to have multiple instances of a Single Database,
 */

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(ListFragment listFragment);
    void inject(CreateFragment createFragment);
    void inject(DetailFragment detailFragment);
    void inject(TabataFragment tabataFragment);

    Application application();

}
