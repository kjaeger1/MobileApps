package edu.uco.hsung.uicab1;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ActionMode.Callback actionModeCallback;
    private ActionMode actionMode;
    private TextView tv1;

    private String[] info = { "John Smith", "johnsmith@gmail.com",
            "405.123.4567" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText(info[0] + " " + info[1] + " " + info[2]);
        // CAB for TextView
        actionModeCallback = new ActionModeCallback();
        tv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionMode != null) {
                    return false;
                }
                // Start the Contextual Action Bar using the ActionMode.Callback
                actionMode = MainActivity.this
                        .startActionMode(actionModeCallback);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private class ActionModeCallback implements ActionMode.Callback {

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_call:
                    Toast.makeText(getApplicationContext(), "callto: " + info[2],
                            Toast.LENGTH_SHORT).show();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.menu_edit:
                    Toast.makeText(getApplicationContext(),
                            "Edit name: " + info[0], Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.menu_email:
                    Toast.makeText(getApplicationContext(), "mailto: " + info[1],
                            Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }

        // Called each time the action mode is shown.
        // Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // return false if nothing is done
        }
    }
}
