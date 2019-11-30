package roomdemo.wiseass.com.roomdemo.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class ListItemDatabase_Impl extends ListItemDatabase {
  private volatile ListItemDao _listItemDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ListItem` (`itemId` TEXT NOT NULL, `message` TEXT, `preparation` INTEGER NOT NULL, `travail` INTEGER NOT NULL, `reposcourt` INTEGER NOT NULL, `nbrCycles` INTEGER NOT NULL, `nbrSeries` INTEGER NOT NULL, `reposLong` INTEGER NOT NULL, `colorResource` INTEGER NOT NULL, PRIMARY KEY(`itemId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c8c77dcc0fdcd415da65303a44d1e838\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ListItem`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsListItem = new HashMap<String, TableInfo.Column>(9);
        _columnsListItem.put("itemId", new TableInfo.Column("itemId", "TEXT", true, 1));
        _columnsListItem.put("message", new TableInfo.Column("message", "TEXT", false, 0));
        _columnsListItem.put("preparation", new TableInfo.Column("preparation", "INTEGER", true, 0));
        _columnsListItem.put("travail", new TableInfo.Column("travail", "INTEGER", true, 0));
        _columnsListItem.put("reposcourt", new TableInfo.Column("reposcourt", "INTEGER", true, 0));
        _columnsListItem.put("nbrCycles", new TableInfo.Column("nbrCycles", "INTEGER", true, 0));
        _columnsListItem.put("nbrSeries", new TableInfo.Column("nbrSeries", "INTEGER", true, 0));
        _columnsListItem.put("reposLong", new TableInfo.Column("reposLong", "INTEGER", true, 0));
        _columnsListItem.put("colorResource", new TableInfo.Column("colorResource", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysListItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesListItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoListItem = new TableInfo("ListItem", _columnsListItem, _foreignKeysListItem, _indicesListItem);
        final TableInfo _existingListItem = TableInfo.read(_db, "ListItem");
        if (! _infoListItem.equals(_existingListItem)) {
          throw new IllegalStateException("Migration didn't properly handle ListItem(roomdemo.wiseass.com.roomdemo.data.ListItem).\n"
                  + " Expected:\n" + _infoListItem + "\n"
                  + " Found:\n" + _existingListItem);
        }
      }
    }, "c8c77dcc0fdcd415da65303a44d1e838");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "ListItem");
  }

  @Override
  public ListItemDao listItemDao() {
    if (_listItemDao != null) {
      return _listItemDao;
    } else {
      synchronized(this) {
        if(_listItemDao == null) {
          _listItemDao = new ListItemDao_Impl(this);
        }
        return _listItemDao;
      }
    }
  }
}
