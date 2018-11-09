package univ.etu.tachemun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import univ.etu.tachemun.db.managers.UtilisateurManager;

public class Connexion extends AppCompatActivity {
    List<String> messagesErrors;
    private Button inscriptionButton;
    private Button connexionButton;
    private TextView messageErrorConnexion;
    private EditText inputPseudo;
    private EditText inputPassword;

    //UtilisateurManager utilisateurManager = new UtilisateurManager(Connexion.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        inscriptionButton = (Button) findViewById(R.id.buttonInscription);
        inputPseudo = (EditText) findViewById(R.id.connexion_input_pseudo);
        inputPassword = (EditText) findViewById(R.id.connexion_input_password);
        messageErrorConnexion = (TextView) findViewById(R.id.connexion_message_error);
        connexionButton = (Button) findViewById(R.id.buttonConnexion);


        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Connexion.this, Inscription.class);

                //ce qui a été mis dans les champs doit etre transmis à l'activity Inscription
                String userName = getInputPseudo();
                if (!userName.isEmpty())
                    i.putExtra("PSEUDO", userName);
                startActivity(i);
            }
        });

        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagesErrors = new ArrayList<>();


                if (!(checkPseudoReconized(getInputPseudo()))) {
                    messagesErrors.add("Pseudo inconnu");
                }
                if (!(checkPseudoPassword(getInputPseudo(), getInputPassword()))) {
                    messagesErrors.add("Le pseudo/mot de passe ne concordent pas");
                }

                if (messagesErrors.isEmpty()) {
                    Intent i = new Intent(Connexion.this, Listeliste.class);
                    startActivity(i);
                } else {
                    messageErrorConnexion.setText(messagesErrors.toString());

                    //test a supp
                    Intent i = new Intent(Connexion.this, Listeliste.class);
                    startActivity(i);

                }

            }
        });
    }

    private String getInputPseudo() {
        return inputPseudo.getText().toString();
    }


    private String getInputPassword() {
        return inputPassword.getText().toString();
    }


    private boolean checkPseudoReconized(String pseudo) {

        //return utilisateurManager.isPseudoInDb(pseudo);
        return false;
    }


    private boolean checkPseudoPassword(String pseudo, String password) {


        //return utilisateurManager.connectUser(pseudo, password);
        return false;
    }

}
