package e.allou.spoilers.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DataEntity.class}, version =  1)
public abstract class SpoilerDB extends RoomDatabase {
    public abstract DataDao dataDao();

}
