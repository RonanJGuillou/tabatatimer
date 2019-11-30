package roomdemo.wiseass.com.roomdemo.dependencyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import roomdemo.wiseass.com.roomdemo.data.ListItemDao;
import roomdemo.wiseass.com.roomdemo.data.ListItemDatabase;
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository;
import roomdemo.wiseass.com.roomdemo.viewmodel.CustomViewModelFactory;

@Module
public class RoomModule {

    private final ListItemDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                ListItemDatabase.class,
                "ListItem.db"
        ).build();

    }

    @Provides
    @Singleton
    ListItemRepository provideListItemRepository(ListItemDao listItemDao){
        return new ListItemRepository(listItemDao);
    }

    @Provides
    @Singleton
    ListItemDao provideListItemDao(ListItemDatabase database){
        return database.listItemDao();
    }

    @Provides
    @Singleton
    ListItemDatabase provideListItemDatabase(Application application){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(ListItemRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
