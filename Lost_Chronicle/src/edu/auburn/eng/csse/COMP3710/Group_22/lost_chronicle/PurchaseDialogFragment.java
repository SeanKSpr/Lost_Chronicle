package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;

public class PurchaseDialogFragment extends DialogFragment {
	
	private final PurchaseDialogFragment mDialog = this;
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Select a purchase type");
		
		builder.setPositiveButton("Gold", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Proceed to next Dialog to confirm payment using gold
				
			}
		});
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//Do nothing (window automatically closes)
			}
		});
		
		builder.setNeutralButton("Cash", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				FragmentTransaction transaction = mDialog.getActivity().getFragmentManager().beginTransaction();
				MicrotransactionDialog microDialog = new MicrotransactionDialog();
				transaction.add(microDialog, "key").addToBackStack(null).commit();
			}
		});
		
		return builder.create();
	}
}
