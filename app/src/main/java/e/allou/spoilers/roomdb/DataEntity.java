package e.allou.spoilers.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataEntity {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "titre")
    public String titre;

    @ColumnInfo(name= "synopsis")
    public String synopsis;


}
