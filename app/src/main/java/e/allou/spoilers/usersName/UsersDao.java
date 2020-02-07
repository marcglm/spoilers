package e.allou.spoilers.usersName;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM users")
    List<UsersEntity> getall();

    @Query("SELECT * FROM users WHERE email LIKE :email")
    UsersEntity findUserByEmail(String email);

    @Insert
    void insertAll (UsersEntity... usersEntities);

    @Delete
    void delete (UsersEntity... usersEntities);
}
