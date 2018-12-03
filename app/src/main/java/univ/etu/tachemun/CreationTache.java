package univ.etu.tachemun;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import univ.etu.tachemun.TimeDate.DatePickerFragment;
import univ.etu.tachemun.TimeDate.TimePickerFragment;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.TacheManager;

public class CreationTache extends AppCompatActivity {


    private EditText nomTache;
    private EditText descriptionTache;
    private Button printButton;

    private Button timePicker;
    private Button datePicker;

    private Button creaTache;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_tache);
        nomTache = (EditText) findViewById(R.id.cree_nouv_Tache_nomTache_input);
        descriptionTache = (EditText) findViewById(R.id.nouvTache_descr_input);

        creaTache = (Button) findViewById(R.id.cree_nouvTache);
        printButton = (Button) findViewById(R.id.tache_print_button);
        creaTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifTitreTache()) {
                    Tache tache = new Tache(getIntent().getIntExtra("ID_LISTE", -1)
                            , getIntent().getStringExtra("ID_UTILISATEUR"), getTitreTache()
                            , getDescriptionTache(), 0,
                            -1);


                    //Log.i(getClass().toString(), tache.toString());

                    TacheManager tacheManager = new TacheManager(CreationTache.this);
                    tacheManager.insert(tache);

                    //data ne sert a rien c'est un test
                    Intent data = new Intent();
                    setResult(1, data);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreationTache.this);
                    builder.setMessage("Le nom de votre tache est incorrect.");
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
                }
            }
        });




    }

    public void showTimePickerDialog(View v) {
        timePicker = (Button) findViewById(R.id.settimeTache);
        DialogFragment newFragment = new TimePickerFragment(timePicker);
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void showDatePickerDialog(View v) {
        datePicker = (Button) findViewById(R.id.setdateTache);
        DialogFragment newFragment = new DatePickerFragment(datePicker);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void printTache(View view) {

    }

    private String getTitreTache() {
        return this.nomTache.getText().toString();
    }

    private String getDescriptionTache() {
        return this.descriptionTache.getText().toString();
    }

    private Date getdateHeureEcheanceFromPickers() {

        return null;
    }

    private boolean verifTitreTache() {
        return !(getTitreTache().length() == 0);
    }
}
