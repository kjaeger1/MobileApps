package edu.uco.hsung.uicheckradiotoggle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CheckRadioToggleActivity extends Activity {

    private CheckBox checkBoxAndroid;
    private CheckBox checkBoxiPhone;
    private RadioButton radioButtonYes;
    private RadioButton radioButtonNo;
    private RadioButton radioButtonMaybe;
    private ToggleButton toggleButton;
    private Switch switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_radio_toggle);

        checkBoxAndroid = (CheckBox) findViewById(R.id.checkbox_android);
        checkBoxiPhone = (CheckBox) findViewById(R.id.checkbox_iphone);

        CheckBoxListener checkBoxListener = new CheckBoxListener();
        checkBoxAndroid.setOnClickListener(checkBoxListener);
        checkBoxiPhone.setOnClickListener(checkBoxListener);

        radioButtonYes = (RadioButton) findViewById(R.id.radio_yes);
        radioButtonNo = (RadioButton) findViewById(R.id.radio_no);
        radioButtonMaybe = (RadioButton) findViewById(R.id.radio_maybe);

        RadioButtonListener radioButtonListener = new RadioButtonListener();
        radioButtonYes.setOnClickListener(radioButtonListener);
        radioButtonNo.setOnClickListener(radioButtonListener);
        radioButtonMaybe.setOnClickListener(radioButtonListener);

        toggleButton = (ToggleButton) findViewById(R.id.toggle_button);
        switchButton = (Switch) findViewById(R.id.switch_button);

        toggleButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        boolean on = ((ToggleButton) buttonView).isChecked(); // == isChecked parameter
                        if (on) {
                            toastIt("Ru~~~~~n~~~~~ing!!!");
                        } else {
                            toastIt("STOPPED!!!!!!");
                        }
                    }
                });

        switchButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        boolean on = ((Switch) buttonView).isChecked(); // == isChecked parameter
                        if (on) {
                            toastIt("Slide Swich is ON");
                        } else {
                            toastIt("Slide Switch is OFF");
                        }
                    }
                });
    }

    class CheckBoxListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox) view).isChecked();

            // find which CheckBox was clicked
            switch (view.getId()) {
                case R.id.checkbox_android:
                    toastIt(checkBoxAndroid.getText() + ": " + checked);
                    break;
                case R.id.checkbox_iphone:
                    toastIt(checkBoxiPhone.getText() + ": " + checked);
                    break;
            }
        }
    }

    class RadioButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String buttonName;
            // find which button was clicked
            switch (view.getId()) {
                case R.id.radio_yes:
                    buttonName = radioButtonYes.getText().toString();
                    break;
                case R.id.radio_no:
                    buttonName = radioButtonNo.getText().toString();
                    break;
                case R.id.radio_maybe:
                    buttonName = radioButtonMaybe.getText().toString();
                    break;
                default:
                    buttonName = "Time to buy a new computer & phone ^_^";
            }

            toastIt(buttonName + " was clicked!");
        }
    }


    private void toastIt(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}
