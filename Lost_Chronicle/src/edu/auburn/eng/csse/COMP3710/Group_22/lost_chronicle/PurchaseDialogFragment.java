package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;

public class PurchaseDialogFragment extends DialogFragment {
	public static final String PURCHASE_DIALOG_KEY = "edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle.PURCHASE_DIALOG_KEY";
	private final PurchaseDialogFragment mDialog = this;
	StoreCommunicator mCommunicator;
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCommunicator = (StoreCommunicator) activity;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Purchasable item = this.getArguments().getParcelable(Selection_Screen.PURCHASE_KEY);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Select a purchase type");
		
		builder.setPositiveButton("Pay in gold: " + item.getPrice() + "G", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO unlock purchasable and update database with new purchasable information
				// TODO if not affordable, then display error but do not dismiss OR you could not setPositive button because you can't afford it.
				mCommunicator.updatePurchasable(item);
				mDialog.dismiss();
			}
		});
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mDialog.dismiss();
			}
		});
		
		builder.setNeutralButton("Cash", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				FragmentTransaction transaction = mDialog.getActivity().getFragmentManager().beginTransaction();
				MicrotransactionDialog microDialog = new MicrotransactionDialog();
				
				Bundle args = new Bundle();
				args.putParcelable(Selection_Screen.PURCHASE_KEY, mDialog.getArguments().getParcelable(Selection_Screen.PURCHASE_KEY));
				microDialog.setArguments(args);
				
				transaction.add(microDialog, MicrotransactionDialog.MICROTRANSACTION_KEY)
				.addToBackStack(MicrotransactionDialog.MICROTRANSACTION_KEY).commit();
				mDialog.dismiss();
			}
		});
		
		return builder.create();
	}
}
