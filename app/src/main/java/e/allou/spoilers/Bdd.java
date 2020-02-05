package e.allou.spoilers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Bdd extends AppCompatActivity {

    private static final String KEY_SYNOPSIS = "synopsis" ;
    private static final String KEY_CONTENU = "contenu" ;
    private static final String KEY_TITRE = "titre";
    private TextView textView;
    private EditText editText;
    private String textInput;
    //DB VARIABLE
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bdd);
        textView = (TextView) findViewById(R.id.textdata);
        textView.setMovementMethod(new ScrollingMovementMethod());
        editText = (EditText) findViewById(R.id.editTextId);



        note = db.collection("resume").document("naruto");




        String text = "what up";

        //Setting Textview
        textView.setText(text);

    }
    public void loadNote(View view){
        textInput = editText.getText().toString();
        textInput = textInput.toLowerCase();
        note = db.collection("resume").document(textInput);
        note.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override

                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Toast.makeText(Bdd.this,"Connexion reussi",Toast.LENGTH_SHORT).show();
                        if(documentSnapshot.exists()){

                            String synopsis = documentSnapshot.getString(KEY_SYNOPSIS);
                            String titre = documentSnapshot.getString(KEY_TITRE);

                            //Map <String, Object> note = documentSnapshot.getData();
                            //A voir

                            textView.setText("Title :"+ titre +"\n" +"Synopsis :" + synopsis);

                        }
                        else{
                            Toast.makeText(Bdd.this,"le document n'existe pas",Toast.LENGTH_SHORT).show();
                            //TODO : Proposer de Créer un SPOILER
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Bdd.this,"Erreur !",Toast.LENGTH_SHORT).show();


                    }
                });
    }



    public void startcreatespoiler(View view) {
        Intent intent = new Intent(this, CreateSpoiler.class);
        startActivity(intent);

    }
}
