package edu.uco.hsung.uispinnerpicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the current time as the default value
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog

		TimePickerDialog dialog = new TimePickerDialog(getActivity(), this,
				hour, minute, DateFormat.is24HourFormat(getActivity()));

		return dialog;
	}

	// Listener for the TimePickerDialog
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		
		// The time is chosen by the user
		Toast.makeText(getActivity(),
				"hour:"+hourOfDay+" min:"+minute,
				Toast.LENGTH_SHORT).show();

	}

}
