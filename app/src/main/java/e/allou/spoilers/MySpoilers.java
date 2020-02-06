package e.allou.spoilers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import androidx.room.Room;
import e.allou.spoilers.roomdb.DataEntity;
import e.allou.spoilers.roomdb.SpoilerDB;

public class MySpoilers extends AppCompatActivity {

    GridView gridView;
    TextView mySpoilerSynopsis;
    TextView mySpoilerTitre;
    private final String TAG = "MySpoilers";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spoilers);
        gridView = findViewById(R.id.gridViewSpoiler);
        findViewById(R.id.mySpoilerSynopsis);
        findViewById(R.id.mySpoilerTitre);
        SpoilerDB spoilerDB = Room.databaseBuilder(getApplicationContext(), SpoilerDB.class, "dataentity").allowMainThreadQueries().build();
        final List<DataEntity> entities = spoilerDB.dataDao().getall();

        CustomAdapter customAdapter = new CustomAdapter(this,entities);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentShowSpoiler = new Intent(MySpoilers.this, ShowSpoiler.class);
                DataEntity entity = entities.get(position);
                Gson gson = new Gson();
                String json = gson.toJson(entity);
                intentShowSpoiler.putExtra("tag", TAG);
                intentShowSpoiler.putExtra("spoiler", json);
             //   Toast.makeText(MySpoilers.this, "check" +entities.get(position).synopsis, Toast.LENGTH_SHORT).show();
                startActivity(intentShowSpoiler);
            }
        });

    }
}
