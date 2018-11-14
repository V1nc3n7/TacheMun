package univ.etu.tachemun;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import univ.etu.tachemun.TimeDate.DatePickerFragment;
import univ.etu.tachemun.TimeDate.TimePickerFragment;

public class CreationTache extends AppCompatActivity {


    private EditText nomTache;
    private EditText descriptionTache;
    private Button createButton;
    private Button printButton;

    private Button timePicker;
    private Button datePicker;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_tache);
        nomTache = (EditText) findViewById(R.id.cree_nouv_Tache_nomTache_input);
        descriptionTache = (EditText) findViewById(R.id.nouvListeTache_descr_input);
        ;

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
}
