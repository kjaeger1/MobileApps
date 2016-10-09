package edu.uco.hsung.uccab2;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private AbsListView.MultiChoiceModeListener multiChoiceModeListener;
    private ListView listview;
    ArrayAdapter<Contact> adapter;

    private Contact[] myContacts = {
            new Contact("mary", "mary@uco.edu", "405.111.2222"),
            new Contact("david", "david@uco.edu", "405.222.3333"),
            new Contact("stacy", "stacy@uco.edu", "405.333.4444"),
            new Contact("kelly", "kelly@uco.edu", "405.444.5555"),
            new Contact("paul", "paul@uco.edu", "405.555.6666"),
            new Contact("steve", "steve@uco.edu", "782.111.2222"),
            new Contact("mark", "mark@uco.edu", "782.222.3333"),
            new Contact("lucy", "lucy@uco.edu", "782.333.4444"),
            new Contact("phoebe", "phoebe@uco.edu", "782.444.5555"),
            new Contact("sue", "sue@uco.edu", "782.555.6666"),
            new Contact("bob", "bob@uco.edu", "213.333.4444"),
            new Contact("mike", "mike@uco.edu", "782.333.4444"),
            new Contact("michael", "michael@uco.edu", "782.444.5555"),
            new Contact("will", "will@uco.edu", "782.555.6666"),
            new Contact("bill", "bill@uco.edu", "213.333.4444"),
            new Contact("zach", "zach@uco.edu", "405.111.2222"),
            new Contact("mel", "mel@uco.edu", "405.222.3333"),
            new Contact("song", "song@uco.edu", "405.333.4444"),
            new Contact("may", "may@uco.edu", "405.333.4444"),
            new Contact("sarah", "sarah@uco.edu", "213.444.5555"),
            new Contact("april", "april@uco.edu", "213.555.6666") };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CAB for the list view
        listview = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<Contact>(MainActivity.this,
                android.R.layout.simple_list_item_activated_1, myContacts);

        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        multiChoiceModeListener = new MultiChoiceModeListener();
        listview.setMultiChoiceModeListener(multiChoiceModeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private class MultiChoiceModeListener implements
            AbsListView.MultiChoiceModeListener {

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
            // Respond to clicks on the actions in the CAB
            switch (item.getItemId()) {
                case R.id.menu_call:
                    performCall();
                    actionMode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.menu_email:
                    performEmail();
                    actionMode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.menu_edit:
                    performEdit();
                    actionMode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            // Inflate the menu for the CAB
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            // Here you can make any necessary updates to the activity when
            // the CAB is removed. By default, selected items are
            // deselected/unchecked.
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            // Here you can perform updates to the CAB due to
            // an invalidate() request
            return false;
        }

        @Override
        public void onItemCheckedStateChanged(ActionMode actionMode,
                                              int position, long id, boolean checked) {
            // Here you can do something when items are selected/de-selected,
            // such as update the title in the CAB
            actionMode.setSubtitle("(" + listview.getCheckedItemCount()
                    + " selected)");
        }

    }

    private void performCall() {
        SparseBooleanArray selected = listview.getCheckedItemPositions();
        String selectedNames = "";
        for (int i = 0; i < selected.size(); i++) {
            if (selected.valueAt(i)) {
                int pos = selected.keyAt(i);
                selectedNames += " " + myContacts[pos];
            }
        }
        Toast.makeText(MainActivity.this, "Call: " + selectedNames,
                Toast.LENGTH_SHORT).show();
    }

    private void performEmail() {
        SparseBooleanArray selected = listview.getCheckedItemPositions();
        String selectedNames = "";
        for (int i = 0; i < selected.size(); i++) {
            if (selected.valueAt(i)) {
                int pos = selected.keyAt(i);
                selectedNames += " " + myContacts[pos].getEmail();
            }
        }
        Toast.makeText(MainActivity.this, "Email: " + selectedNames,
                Toast.LENGTH_SHORT).show();
    }

    private void performEdit() {
        SparseBooleanArray selected = listview.getCheckedItemPositions();
        String selectedNames = "";
        for (int i = 0; i < selected.size(); i++) {
            if (selected.valueAt(i)) {
                int pos = selected.keyAt(i);
                selectedNames += " " + myContacts[pos];
            }
        }
        Toast.makeText(MainActivity.this, "Edit: " + selectedNames,
                Toast.LENGTH_SHORT).show();
    }
}
