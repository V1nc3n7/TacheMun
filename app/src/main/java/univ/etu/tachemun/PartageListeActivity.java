package univ.etu.tachemun;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import univ.etu.tachemun.db.tableclass.PartageListe;
import univ.etu.tachemun.db.tablemanagers.PartageListeManager;
import univ.etu.tachemun.db.tablemanagers.UtilisateurManager;

public class PartageListeActivity extends AppCompatActivity {
    private EditText editTextIdInvite;
    private Button buttonPartage;

    private String getIdinvite() {
        return editTextIdInvite.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partage_liste);
        editTextIdInvite = (EditText) findViewById(R.id.partage_input_pseudo_invite);
        buttonPartage = (Button) findViewById(R.id.buttonPartageListe);

        buttonPartage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String invite = getIdinvite();
                if (!pseudoKnown(invite)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PartageListeActivity.this);
                    builder.setMessage("Ce pseudo est inconnu");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Suivant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Activity will continue",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    PartageListeManager partageListeManager = new PartageListeManager(PartageListeActivity.this);
                    int id_liste = getIntent().getIntExtra("ID_LISTETACHE", -1);
                    String userName = getIntent().getStringExtra("ID_UTILISATEUR");
                    partageListeManager.insert(new PartageListe(userName, id_liste, invite, ""));
                    //TODO mettre un message 'liste partagée'
                    Intent i = new Intent(PartageListeActivity.this, ListeListeTaches.class);
                    i.putExtra("ID_UTILISATEUR", userName);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private boolean pseudoKnown(String pseudo) {
        UtilisateurManager utilisateurManager = new UtilisateurManager(PartageListeActivity.this);
        return utilisateurManager.isPseudoInDb(pseudo);
    }
}
