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

import univ.etu.tachemun.db.tablemanagers.UtilisateurManager;

public class Connexion extends AppCompatActivity {
    private List<String> messagesErrors;
    private Button inscriptionButton;
    private Button connexionButton;
    private TextView messageErrorConnexion;
    private EditText inputPseudo;
    private EditText inputPassword;


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
                String userName = getInputPseudo();
                if (!userName.isEmpty())
                    i.putExtra("ID_UTILISATEUR", userName);
                startActivityForResult(i, 0);
            }
        });

        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messagesErrors = new ArrayList<>();


                if (!(checkPseudoReconized(getInputPseudo()))) {
                    messagesErrors.add("Pseudo inconnu");
                } else if (!(checkPseudoPassword(getInputPseudo(), getInputPassword()))) {
                    messagesErrors.add("Le pseudo/mot de passe ne concordent pas");
                }

                if (messagesErrors.isEmpty()) {
                    Intent i = new Intent(Connexion.this, ListeListeTaches.class);
                    i.putExtra("ID_UTILISATEUR", getInputPseudo());

                    startActivity(i);
                    finish();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Connexion.this);
                    StringBuilder mess = new StringBuilder();
                    for (int i = 0; i < messagesErrors.size(); i++) {
                        mess.append("").append(messagesErrors.get(i)).append("\n");
                    }
                    builder.setMessage(mess.toString());
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
        UtilisateurManager utilisateurManager = new UtilisateurManager(Connexion.this);
        return utilisateurManager.isPseudoInDb(pseudo);
    }

    private boolean checkPseudoPassword(String pseudo, String password) {
        UtilisateurManager utilisateurManager = new UtilisateurManager(Connexion.this);
        return utilisateurManager.connectUser(pseudo, password);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == 0) {
                //ce passe rien
            } else {
                inputPseudo = (EditText) findViewById(R.id.connexion_input_pseudo);
                inputPseudo.setText(data.getStringExtra("ID_UTILISATEUR"));
            }
        }
    }
}
