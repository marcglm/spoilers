package e.allou.spoilers;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import e.allou.spoilers.roomdb.DataEntity;

public class ShowSpoiler extends AppCompatActivity {
    private TextView titreView;
    private TextView synopsisView;
    private final String TAG ="tag";
    private final String TAG_CAMERA_ACTIVITY = "CameraActivity";
    private final String TAG_MY_SPOILERS = "MySpoilers";
    private final String KEY_TITRE = "titre";
    private final String KEY_SYNOPSIS = "synopsis";


    private String tagActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_spoiler);
        titreView = findViewById(R.id.showSpoilerTitreId);
        synopsisView = findViewById(R.id.showSpoilerSynopsisId);
        Intent intentSpoiler  = getIntent();
        String titre = "titre null";
        String synopsis = "synopsis null";
        DataEntity spoiler;
        Gson gson = new Gson();
        tagActivity = getIntent().getStringExtra(TAG);
        switch (tagActivity){
            case TAG_MY_SPOILERS:
                spoiler = gson.fromJson(getIntent().getStringExtra("spoiler"), DataEntity.class);

                titre = spoiler.titre;
                synopsis = spoiler.synopsis;
                break;
            case TAG_CAMERA_ACTIVITY:
                titre = getIntent().getStringExtra(KEY_TITRE);
                synopsis = getIntent().getStringExtra(KEY_SYNOPSIS);

        }

        titreView.setText(titre);
        synopsisView.setText(synopsis);

    }
}
