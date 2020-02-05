package e.allou.spoilers.roomdb;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

public interface DataDao {

    @Query("SELECT * FROM dataentity")
    List<DataEntity> getall();

    @Query("SELECT * FROM dataentity WHERE titre LIKE :usertitre")
    DataEntity findByTitre(String usertitre);

    @Insert
    void insertAll (DataEntity... dataEntities);

    @Delete
    void delete (DataEntity... dataEntities);
}
