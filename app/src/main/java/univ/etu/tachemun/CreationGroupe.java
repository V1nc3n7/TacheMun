package univ.etu.tachemun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import univ.etu.tachemun.db.managers.GroupeManager;
import univ.etu.tachemun.db.managers.ListeTacheGroupeManager;
import univ.etu.tachemun.db.managers.ListeTacheManager;
import univ.etu.tachemun.db.managers.MembreManager;
import univ.etu.tachemun.db.tableclass.Groupe;
import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.ListeTacheGroupe;
import univ.etu.tachemun.db.tableclass.Membre;

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
                //il faut pseudoUtilisateur
                String pseudo = getIntent().getStringExtra("ID_USER");

                long milis = System.currentTimeMillis();

                Groupe g = new Groupe(-1, getNomGroupeText(), pseudo, getPriveSwitch(), getDescriptionText(), milis);
                int idG = (int) manager.insert(g);

                ListeTache listeTache = new ListeTache(-1, "Liste principale", false, "Liste contenant les tâches à réaliser par les membres du groupe", milis, false, -1, 0);
                ListeTacheManager ltm = new ListeTacheManager(CreationGroupe.this);
                int idl = (int) ltm.insert(listeTache);
                ListeTacheGroupeManager ltgm = new ListeTacheGroupeManager(CreationGroupe.this);
                ListeTacheGroupe ltg = new ListeTacheGroupe(-1, idG, idl, pseudo);
                ltgm.insert(ltg);
                MembreManager mm = new MembreManager(CreationGroupe.this);
                Membre m = new Membre(-1, pseudo, milis, idG);
                mm.insert(m);



            }
        });

    }


    private String getNomGroupeText() {
        return nomGroupeText.getText().toString();
    }

    private String getDescriptionText() {
        return descriptionText.getText().toString();
    }

    public boolean getPriveSwitch() {
        return priveSwitch.isChecked();
    }
}
