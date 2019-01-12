package univ.etu.tachemun;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private TextView date;
    private TextView heure;

    private Spinner spinnerP;
    private int priorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_tache);
        nomTache = (EditText) findViewById(R.id.cree_nouv_Tache_nomTache_input);
        descriptionTache = (EditText) findViewById(R.id.nouvTache_descr_input);

        priorite = 0;

        spinnerP = (Spinner) findViewById(R.id.spinner_priorité);
        List<String> list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerP.setAdapter(dataAdapter);

        spinnerP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                priorite = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                priorite = 0;
            }
        });


        date = (TextView) findViewById(R.id.date);
        heure = (TextView) findViewById(R.id.heure);

        creaTache = (Button) findViewById(R.id.cree_nouvTache);
        creaTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifTitreTache()) {

                    Date d = new Date();
                    d.setTime(Long.parseLong("" + date.getText()));
                    String a = (String) heure.getText();
                    String[] t = a.split(":");
                    String h = t[0];
                    String m = t[1];
                    d.setHours(Integer.parseInt(h));
                    d.setMinutes(Integer.parseInt(m));
                    d.setSeconds(0);

                    Tache tache;

                    if (date.getText().equals("0")) {
                        tache = new Tache(getIntent().getIntExtra("ID_LISTE", -1)
                                , getIntent().getStringExtra("ID_UTILISATEUR"), getTitreTache()
                                , getDescriptionTache(), priorite,
                                -1);
                    } else {
                        tache = new Tache(getIntent().getIntExtra("ID_LISTE", -1)
                                , getIntent().getStringExtra("ID_UTILISATEUR"), getTitreTache()
                                , getDescriptionTache(), priorite,
                                d.getTime());
                    }

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
        heure = (TextView) findViewById(R.id.heure);
        timePicker = (Button) findViewById(R.id.settimeTache);
        DialogFragment newFragment = new TimePickerFragment(timePicker, heure);
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void showDatePickerDialog(View v) {
        date = (TextView) findViewById(R.id.date);
        datePicker = (Button) findViewById(R.id.setdateTache);
        DialogFragment newFragment = new DatePickerFragment(datePicker, date);
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
        return !(getTitreTache().isEmpty());
    }
}
