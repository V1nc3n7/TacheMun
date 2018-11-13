package univ.etu.tachemun;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private UtilisateurManager utilisateurManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        inscriptionButton = (Button) findViewById(R.id.buttonInscription);
        inputPseudo = (EditText) findViewById(R.id.connexion_input_pseudo);
        inputPassword = (EditText) findViewById(R.id.connexion_input_password);
        messageErrorConnexion = (TextView) findViewById(R.id.connexion_message_error);
        connexionButton = (Button) findViewById(R.id.buttonConnexion);


        utilisateurManager = new UtilisateurManager(Connexion.this);


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
                    i.putExtra("PSEUDO", getInputPseudo());
                    startActivity(i);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Connexion.this);
                    String mess = "";
                    for (int i = 0; i < messagesErrors.size(); i++) {
                        mess += "" + messagesErrors.get(i) + "\n";
                    }
                    builder.setMessage(mess);
                    builder.setCancelable(true);
                    builder.setPositiveButton("Suivant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Suivant",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    //messageErrorConnexion.setText(messagesErrors.toString());
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

        return utilisateurManager.isPseudoInDb(pseudo);
    }


    private boolean checkPseudoPassword(String pseudo, String password) {

        return utilisateurManager.connectUser(pseudo, password);
    }

}
