package roomdemo.wiseass.com.roomdemo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * entities:
 * The Models which we will use to convert to/from data stored in an SQL Table.
 * <p>
 * version:
 * In order to preserve Data when an SQL Table changes it's structure, we increment the version
 * number. Room also requires that you create a "Migration" class which allow the Database to be
 * restructured as desired, based on the Database structure of the initial version, to the structure
 * after the version is updated.
 */

@Database(entities = {ListItem.class}, version = 1)
public abstract class ListItemDatabase extends RoomDatabase {

    public abstract ListItemDao listItemDao();

}
