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

import univ.etu.tachemun.validators.MailValidator;
import univ.etu.tachemun.validators.Validator;

public class Inscription extends AppCompatActivity {
    private static final String CHARACCEPTED = "AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjkklmwxcvbn1234567890&é(§è!çà)_-";


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
/*
        confirmation=new Button(this);
        messageErrorOutput=new TextView(this);
        pseudoInput=new EditText(this);
        password1Input=new EditText(this);
        password2Input=new EditText(this);
        mailInput=new EditText(this);

*/

        confirmation = (Button) findViewById(R.id.inscription_confirmation_button);
        messageErrorOutput = (TextView) findViewById(R.id.inscription_error_ouptut);
        pseudoInput = (EditText) findViewById(R.id.inscription_pseudo_input);
        password1Input = (EditText) findViewById(R.id.inscription_password1_input);
        password2Input = (EditText) findViewById(R.id.inscription_password2_input);
        mailInput = (EditText) findViewById(R.id.inscription_mail_input);
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagesErrors = new ArrayList<>();
                if (!(pseudoAvailiable())) {
                    messagesErrors.add("Ce pseudo est deja pris");
                }
                if (!(new Validator("0-9a-zA-Z@-_", 4, 32).validate(getPseudoInput()))) {
                    messagesErrors.add("Pseudo Invalide : il doit etre entre 4 et 32 carcteres, et comporter que  AZ az à-9 @ -_");
                }

                if (!(new MailValidator().validate(getMailInput()))) {
                    messagesErrors.add("Mail Invalide");
                }
                if (!checkSamePassword()) {
                    messagesErrors.add("Les mots de passe doivent etre les memes");
                }
                if (!checkPassword()) {
                    messagesErrors.add("Mot de passe trop petit");
                    messageErrorOutput.setText(messagesErrors.toString());
                } else {
                    Intent i = new Intent(Inscription.this, Listeliste.class);
                    startActivity(i);
                }
            }
        });


    }


    /**
     * @return
     */
    public boolean pseudoAvailiable() {
        //TODO
        return true;
    }

    public String getPseudoInput() {
        return pseudoInput.getText().toString();
    }

    public String getPassword1Input() {
        return password1Input.getText().toString();
    }

    public String getPassword2Input() {
        return password2Input.getText().toString();
    }

    public String getMailInput() {
        return mailInput.getText().toString();
    }

    public boolean checkSamePassword() {
        return getPassword1Input().equals(getPassword2Input());
    }

    public boolean checkPassword() {
        return getPassword1Input().length() > 3;
    }
}
