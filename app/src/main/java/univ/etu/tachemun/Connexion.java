package univ.etu.tachemun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Connexion extends AppCompatActivity {
    private Button inscriptionButton;
    private Button connexionButton;
    private TextView messageErrorConnexion;
    private EditText inputPseudo;
    private EditText inputPassword;
    List<String> messagesErrors;

    public static String getSHA256(String input) {

        String toReturn = null;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }
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
                if (!(checkPseudoPassword(getInputPseudo(), getSHA256(getInputPassword())))) {
                    messagesErrors.add("Le pseudo/mot de passe ne concordent pas");
                }

                if (messagesErrors.isEmpty()) {
                    Intent i = new Intent(Connexion.this, Listeliste.class);
                    startActivity(i);
                } else {
                    messageErrorConnexion.setText(messagesErrors.toString());
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
        //TODO

        return true;
    }


    private boolean checkPseudoPassword(String pseudo, String password) {
        //TODO

        return true;
    }

}
