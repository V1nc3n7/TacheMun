package univ.etu.tachemun.TimeDate;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private Button d;
    private Date date;
    private TextView textView;

    @SuppressLint("ValidFragment")
    public DatePickerFragment(Button date, TextView textView) {
        d = date;
        this.date = new Date();
        this.textView = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Do something with the date chosen by the user

        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");

        d.setText(df.format(calendar.getTime()));
        textView.setText("" + calendar.getTime().getTime());

        this.date = calendar.getTime();
    }
}
