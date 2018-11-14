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
import java.util.Random;
import java.util.Set;

import univ.etu.tachemun.db.managers.ListeTacheManager;
import univ.etu.tachemun.db.managers.ProprietaireListeManager;
import univ.etu.tachemun.db.managers.UtilisateurManager;
import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.ProprietaireListe;
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

                // faire un aletDialog

                //Utilisateur utilisateur = new Utilisateur(pseudoInput.getText().toString(),password2Input.getText().toString(),mailInput.getText().toString(),System.currentTimeMillis());

                messagesErrors = new ArrayList<>();
                if (!(pseudoAvailiable())) {
                    messagesErrors.add("Ce pseudo est deja pris");
                }
                if (!(mailKnown())) {
                    messagesErrors.add("Ce pseudo est deja pris");
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
                    ProprietaireListeManager proprietaireListeManager = new ProprietaireListeManager(Inscription.this);
                    ListeTacheManager listeTacheManager = new ListeTacheManager(Inscription.this);

                    Utilisateur user = new Utilisateur(getPseudoInput(), getPassword1Input(), getMailInput(), System.currentTimeMillis());
                    utilisateurManager.insertNew(user);
                    Random random = new Random();
                    ListeTache listeTache = new ListeTache(-1, "Liste principale", true, "Liste principale contenant les tâches à réaliser", System.currentTimeMillis()
                            , false, 0, random.nextInt(10));

                    long id = listeTacheManager.insertNew(listeTache);
                    ProprietaireListe proprietaireListe = new ProprietaireListe(-1, user.getPseudo(), (int) id);

                    proprietaireListeManager.insert(proprietaireListe);
                    Intent i = new Intent(Inscription.this, Listeliste.class);
                    i.putExtra("ID_UTILISATEUR", user.getPseudo());

                    Set<Utilisateur> su = utilisateurManager.getUtilisateurs();
                    for (Utilisateur userwesh : su) {
                        System.out.println(userwesh.toString());
                    }
                    startActivity(i);
                    finish();
                } else {
                    messageErrorOutput.setText(messagesErrors.toString());
                }

            }
        });


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
