package e.allou.spoilers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateSpoiler extends AppCompatActivity {

    private EditText editTextSynopsis;
    private EditText editTextTitre;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Map<String, Object> dt = new HashMap<>();
    private String auteur ="Spoiler 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spoiler);
        editTextSynopsis = (EditText)findViewById(R.id.textcreatespoiler);
        editTextTitre = (EditText)findViewById(R.id.textTitre);
        String spoilerdt = "nothing";


    }

    public void savedata(View view) {
        //Save Data in local #Persistence de donnée
        //TODO : Ajouter les données dans La BDD.
    }

    public void senddata(View view) {
        //Envoyer les données dans la BDD
        dt.put("titre", editTextTitre.getText().toString());
        dt.put("synopsis", editTextSynopsis.getText().toString());

        db.collection("resume")
                .add(dt)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreateSpoiler.this, "Document Ajouté avec succès",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateSpoiler.this, "Erreur lors de l'ajout du document",
                                Toast.LENGTH_SHORT).show();
                    }
                });



    }
}
