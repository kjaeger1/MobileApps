package edu.uco.hsung.uidialogdemo;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uco.hsung.uidialogdemo.CustomLayoutDialogFragment.CustomLayoutListener;
import edu.uco.hsung.uidialogdemo.FireMissilesDialogFragment.FireMissilesListener;
import edu.uco.hsung.uidialogdemo.PickColorDialogFragment.PickColorListener;
import edu.uco.hsung.uidialogdemo.PickToppingsDialogFragment.PickToppingsListener;

public class MainActivity extends Activity implements FireMissilesListener,
        PickColorListener, PickToppingsListener, CustomLayoutListener {

    private TextView textview;
    private static final String TAG = "UIDialogDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);

        Button buttonFire = (Button) findViewById(R.id.fire_button);
        buttonFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireMissilesDialogFragment d = new FireMissilesDialogFragment();
                d.show(getFragmentManager(), "missiles");
            }
        });

        Button buttonColor = (Button) findViewById(R.id.color_button);
        buttonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickColorDialogFragment d = new PickColorDialogFragment();
                d.show(getFragmentManager(), "colors");
            }
        });

        Button buttonToppings = (Button) findViewById(R.id.topping_button);
        buttonToppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickToppingsDialogFragment d = new PickToppingsDialogFragment();
                d.show(getFragmentManager(), "toppings");
            }
        });

        Button buttonCustom = (Button) findViewById(R.id.custom_button);
        buttonCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomLayoutDialogFragment d = new CustomLayoutDialogFragment();
                d.show(getFragmentManager(), "custom");
            }
        });
    }

    @Override
    public void onFireMissilesDialogPositiveClick(DialogFragment dialog) {
        textview.setText("Fire missiles!");
    }

    @Override
    public void onFireMissilesDialogNegativeClick(DialogFragment dialog) {
        textview.setText("Cancel fire missiles!");
    }

    @Override
    public void onPickColorClick(int colorIndex, DialogFragment dialog) {
        String color = getResources().getStringArray(R.array.colors_array)[colorIndex];
        textview.setText(color + " has picked!");
    }

    @Override
    public void onPickToppingsDialogPositiveClick(ArrayList<Integer> items,
                                                  DialogFragment dialog) {
        String[] toppings = getResources().getStringArray(R.array.toppings);
        String selectedItems = "";
        for (int i = 0; i < items.size(); i++) {
            selectedItems += toppings[items.get(i)] + " ";
        }
        textview.setText("Toppings: " + selectedItems);
    }

    @Override
    public void onPickToppingsDialogNegativeClick(DialogFragment dialog) {
        textview.setText("Cancel pick toppings!");
    }

    @Override
    public void onCustomLayoutDialogPositiveClick(String username,
                                                  String password, DialogFragment dialog) {
        textview.setText("Username=" + username + " Password=" + password);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

}
