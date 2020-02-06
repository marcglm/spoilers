package e.allou.spoilers.roomdb;

import android.os.Parcelable;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dataentity")
public class DataEntity  {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "titre")
    public String titre;

    @ColumnInfo(name= "synopsis")
    public String synopsis;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
