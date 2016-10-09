package edu.uco.hsung.uispinnerpicker;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerPickerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_picker);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_classification);
        // create an ArrayAdapter for the spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getApplicationContext(),
                        R.array.array_classification,
                        R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // responding to user selections (Spinner)
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected.
                toastIt(parent.getItemAtPosition(pos) + " is selected");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Callback method to be invoked when the selection disappears
                // from this view. The selection can disappear for instance
                // when touch is activated or when the adapter becomes empty.
                toastIt("Noting was selected");
            }
        });

        Button timeButton = (Button) findViewById(R.id.timepicker);
        timeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogFragment fragment = new TimePickerFragment();
                fragment.show(getFragmentManager(), "timePicker");
            }
        });

        Button dateButton = (Button) findViewById(R.id.datepicker);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "dateicker");
            }
        });
    }

    private void toastIt(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}
