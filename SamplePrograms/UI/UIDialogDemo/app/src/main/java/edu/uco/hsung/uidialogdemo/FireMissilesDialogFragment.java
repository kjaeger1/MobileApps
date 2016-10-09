package edu.uco.hsung.uidialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class FireMissilesDialogFragment extends DialogFragment {
	
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
	public interface FireMissilesListener {
		public void onFireMissilesDialogPositiveClick(DialogFragment dialog);
		public void onFireMissilesDialogNegativeClick(DialogFragment dialog);
	}
	
	// Use this instance of the interface to deliver action events
	FireMissilesListener listener;
	
	// Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (FireMissilesListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement FireMissilesListener");
        }
    }
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_fire_missiles)
               .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       listener.onFireMissilesDialogPositiveClick(FireMissilesDialogFragment.this);
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       listener.onFireMissilesDialogNegativeClick(FireMissilesDialogFragment.this);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}