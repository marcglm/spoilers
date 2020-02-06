package e.allou.spoilers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Parametre extends AppCompatActivity {

    private TextView emailUser;
    private TextView nomUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        findViewById(R.id.parametre_email);
        findViewById(R.id.parametre_nom_user);
        findViewById(R.id.deconnexion);
    }

    public void logout(View view) {
        //TODO : Deconnecter l'utilisateur
    }

    public void startMySpoilerActivity(View view) {
        Intent intentMySpoilers = new Intent(this, MySpoilers.class);
        startActivity(intentMySpoilers);
    }
}
