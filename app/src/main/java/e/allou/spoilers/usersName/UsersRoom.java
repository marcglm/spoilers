package e.allou.spoilers.usersName;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UsersEntity.class}, version =  1)
public abstract class UsersRoom  extends RoomDatabase{
    public abstract UsersDao usersDao();

}
