package univ.etu.tachemun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import univ.etu.tachemun.db.tableclass.Groupe;
import univ.etu.tachemun.db.tablemanagers.GroupeManager;

public class CreationGroupe extends AppCompatActivity {

    private EditText nomGroupeText;
    private EditText descriptionText;
    private Switch priveSwitch;
    private Button confButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_groupe);

        priveSwitch = (Switch) findViewById(R.id.creation_groupe_switch);
        nomGroupeText = (EditText) findViewById(R.id.creation_groupe_input);
        descriptionText = (EditText) findViewById(R.id.creation_groupe_description_input);
        confButton = (Button) findViewById(R.id.creation_groupe_confirmation_button);


        confButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupeManager manager = new GroupeManager(CreationGroupe.this);
                String pseudo = getIntent().getStringExtra("ID_UTILISATEUR");

                Groupe g = new Groupe(getNomGroupeText(), pseudo, getPriveSwitch(), getDescriptionText());
                int idG = (int) manager.insert(g);


            }
        });

    }


    private String getNomGroupeText() {
        return nomGroupeText.getText().toString();
    }

    private String getDescriptionText() {
        return descriptionText.getText().toString();
    }

    private boolean getPriveSwitch() {
        return priveSwitch.isChecked();
    }
}
