package e.allou.spoilers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private String email;
    private String password;
    private FirebaseAuth mAuth;
    private final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = (EditText)findViewById(R.id.emailId);
        editTextPassword = (EditText)findViewById(R.id.paswordId);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void signInUser(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        if(email == null || email.equals("")||!isEmail(email) ) Toast.makeText(this, "Email non valide !", Toast.LENGTH_SHORT).show();
        if(password.length() < 4) Toast.makeText(this, "Mot de passe trop court !", Toast.LENGTH_SHORT).show();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Connexion Réussi ", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            //updateUI(user);

                            //TODO: Renvoyer l'email/nom de l'utilisateur
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Connexion Echoué ", Toast.LENGTH_SHORT).show();

                            //updateUI(null);

                            //TODO: Prooposer de creer un compte:
                        }

                        // ...
                    }
                });
        //Fonction sur Bouton de Connexiont²

    }

    public void signUpUser(View view) {
        //Fonction sur Bouton d'Inscription

        Intent intentInscription = new Intent(this, Inscription.class );
        startActivity(intentInscription);

    }

    public boolean isEmail(String email){
        return email.contains("@");
    }

}
