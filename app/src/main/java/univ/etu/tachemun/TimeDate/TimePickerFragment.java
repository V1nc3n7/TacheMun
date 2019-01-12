package univ.etu.tachemun.TimeDate;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private Button t;
    private int mins;
    private int hours;
    private TextView textView;

    @SuppressLint("ValidFragment")
    public TimePickerFragment(Button time, TextView textView) {
        t = time;
        mins = 0;
        hours = 0;
        this.textView = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        t.setText(new StringBuilder().append("").append(hourOfDay).append("h ").append(minute).toString());
        hours = hourOfDay;
        mins = minute;

        java.text.DateFormat df = new SimpleDateFormat("HH:mm");
        Date d = new Date();
        d.setHours(hours);
        d.setMinutes(mins);

        textView.setText(df.format(d));
    }

    public long getTime() {
        return (hours * 60 * 60) + (mins * 60);
    }

    public int getMins() {
        return mins;
    }

    public int getHours() {
        return hours;
    }
}
