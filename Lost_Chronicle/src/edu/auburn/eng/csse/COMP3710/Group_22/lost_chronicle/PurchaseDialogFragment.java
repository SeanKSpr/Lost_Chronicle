package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class PurchaseDialogFragment extends DialogFragment {
	public static final String PURCHASE_DIALOG_KEY = "edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle.PURCHASE_DIALOG_KEY";
	private final PurchaseDialogFragment mDialog = this;
	private StoreCommunicator mCommunicator;
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCommunicator = (StoreCommunicator) activity;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Purchasable item = this.getArguments().getParcelable(Selection_Screen.PURCHASE_KEY);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Select a purchase type");
		builder.setCancelable(true);
		builder.setPositiveButton("Pay in gold: " + item.getPrice() + "G", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				int amountOfMoney = mCommunicator.getWallet();
				boolean prereqSatisfied = true;
				if (item instanceof Companion) {
					Companion previousTier = (Companion) mCommunicator.getPurchasable((int) (item.getId() - 1));
					
					if (previousTier != null && previousTier.getName().equals(item.getPurchaseName()) && !previousTier.hasBeenPurchased()) {
						prereqSatisfied = false;
						Toast.makeText(mDialog.getActivity(), "You must unlock the previous tier first", Toast.LENGTH_SHORT).show();
					}
				}
				if (prereqSatisfied) {
					if (amountOfMoney < item.getPrice()) {
						Toast.makeText(mDialog.getActivity(), "You do not have enough gold", Toast.LENGTH_SHORT).show();
					}
					else {
						mCommunicator.performTransaction(item.getPrice());
						mCommunicator.updatePurchasable(item);
					}
				}
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
			}
		});
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		return dialog;
	}
	
	@Override
	public void dismiss() {
		super.dismiss();
	}
}
