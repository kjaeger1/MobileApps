package edu.uco.hsung.uispinnerpicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog
		DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
		return dialog;
		
	}

	// The listener when the user chooses a date in the picker
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		
		// The date is chosen by the user
				Toast.makeText(getActivity(),
						"year:"+year+" month:"+month+" day:"+day,
						Toast.LENGTH_SHORT).show();
				
	}

}
