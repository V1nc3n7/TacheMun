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
import univ.etu.tachemun.db.tableclass.Utilisateur;
import univ.etu.tachemun.validators.MailValidator;
import univ.etu.tachemun.validators.PasswordValidator;
import univ.etu.tachemun.validators.PseudoValidator;

public class Inscription extends AppCompatActivity {


    private static final int MAX_LENGHT_PASSWORD = 120;
    private static final int MIN_LENGHT_PASSWORD = 3;
    private Button confirmation;
    private TextView messageErrorOutput;
    private EditText pseudoInput;

    private EditText password1Input;
    private EditText password2Input;
    private EditText mailInput;
    private List<String> messagesErrors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        confirmation = (Button) findViewById(R.id.inscription_confirmation_button);
        messageErrorOutput = (TextView) findViewById(R.id.inscription_error_ouptut);
        pseudoInput = (EditText) findViewById(R.id.inscription_pseudo_input);
        password1Input = (EditText) findViewById(R.id.inscription_password1_input);
        password2Input = (EditText) findViewById(R.id.inscription_password2_input);
        mailInput = (EditText) findViewById(R.id.inscription_mail_input);

        if (getIntent().hasExtra("ID_UTILISATEUR"))
            pseudoInput.setText(getIntent().getStringExtra("ID_UTILISATEUR"));
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                messagesErrors = new ArrayList<>();
                if (!(pseudoAvailiable())) {
                    messagesErrors.add("Ce pseudo est deja pris");
                }
                if (!(mailKnown())) {
                    messagesErrors.add(" MAil déja connu ");
                }
                if (!(new PseudoValidator()).validate(getPseudoInput())) {
                    messagesErrors.add("Pseudo Invalide : il doit etre entre 4 et 32 carcteres, et comporter que  AZ az à-9 @ -_");
                }

                if (!(new MailValidator().validate(getMailInput()))) {
                    messagesErrors.add("Mail Invalide");
                }
                if (!checkSamePassword()) {
                    messagesErrors.add("Les mots de passe doivent etre les mêmes");
                }
                if (!checkPassword()) {
                    messagesErrors.add("Mot de passe trop petit");

                }
                if (messagesErrors.isEmpty()) {

                    UtilisateurManager utilisateurManager = new UtilisateurManager(Inscription.this);


                    Utilisateur user = new Utilisateur(getPseudoInput(), getPassword1Input(), getMailInput(), System.currentTimeMillis());
                    utilisateurManager.insertNew(user);


                    Intent i = new Intent(Inscription.this, Listeliste.class);
                    i.putExtra("ID_UTILISATEUR", user.getPseudo());



                    startActivity(i);
                    setResult(0);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Inscription.this);
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
                    messagesErrors.clear();
                }

            }
        });


    }

    private void addTaches(String pseudo, int id) {

    }

    private boolean mailKnown() {
        UtilisateurManager utilisateurManager = new UtilisateurManager(Inscription.this);
        return !utilisateurManager.isMailInDb(getMailInput());
    }


    /**
     * @return
     */
    private boolean pseudoAvailiable() {

        UtilisateurManager utilisateurManager = new UtilisateurManager(Inscription.this);
        return !utilisateurManager.isPseudoInDb(getPseudoInput());
    }

    private String getPseudoInput() {
        return pseudoInput.getText().toString();
    }

    private String getPassword1Input() {
        return password1Input.getText().toString();
    }

    private String getPassword2Input() {
        return password2Input.getText().toString();
    }

    private String getMailInput() {
        return mailInput.getText().toString();
    }

    private boolean checkSamePassword() {
        return getPassword1Input().equals(getPassword2Input());
    }

    private boolean checkPassword() {
        PasswordValidator p = new PasswordValidator(MIN_LENGHT_PASSWORD, MAX_LENGHT_PASSWORD);
        return p.validate(getPassword1Input());
    }
}
