package e.allou.spoilers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

import e.allou.spoilers.roomdb.DataEntity;

public class ShowSpoiler extends AppCompatActivity {
    private TextView titreView;
    private TextView synopsisView;
    private ImageView imagePersonnage;
    private final String TAG ="tag";
    private final String TAG_CAMERA_ACTIVITY = "CameraActivity";
    private final String TAG_MY_SPOILERS = "MySpoilers";
    private final String KEY_TITRE = "titre";
    private final String KEY_SYNOPSIS = "synopsis";
    private StorageReference mStorageRef;


    private String tagActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_spoiler);
        titreView = findViewById(R.id.showSpoilerTitreId);
        synopsisView = findViewById(R.id.showSpoilerSynopsisId);
        imagePersonnage = findViewById(R.id.imagePersonnage);
        Intent intentSpoiler  = getIntent();
        String titre = "titre null";
        String synopsis = "synopsis null";
        DataEntity spoiler;
        Gson gson = new Gson();
        tagActivity = getIntent().getStringExtra(TAG);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        Toast.makeText(this, mStorageRef.getPath(), Toast.LENGTH_SHORT).show();

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

        File localFile = null;
        try {
            localFile = File.createTempFile("image.jpg", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "erreur file !", Toast.LENGTH_SHORT).show();
        }
        StorageReference riversRef = mStorageRef.child("naruto/sasuke.jpg");
        final File finalLocalFile = localFile;
        final Bitmap bitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
        final long myImage = 1024*1024;
       /* riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                        imagePersonnage.setImageBitmap(bitmap);
                        imagePersonnage.setVisibility(View.VISIBLE);
                        Toast.makeText(ShowSpoiler.this, "image Recupéré", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                Toast.makeText(ShowSpoiler.this, "echec", Toast.LENGTH_SHORT).show();
                // ...
            }
        });
*/
       riversRef.getBytes(myImage)
               .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                   @Override
                   public void onSuccess(byte[] bytes) {
                       Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);


                       imagePersonnage.setImageBitmap(Bitmap.createScaledBitmap(bmp, imagePersonnage.getWidth(),
                               imagePersonnage.getHeight(), false));
                       imagePersonnage.setVisibility(View.VISIBLE);
                       Toast.makeText(ShowSpoiler.this, "image Recupéré", Toast.LENGTH_SHORT).show();

                   }
               });

        titreView.setText(titre);
        synopsisView.setText(synopsis);

    }

    public void loadPersonnage(View view) {

    }
}
