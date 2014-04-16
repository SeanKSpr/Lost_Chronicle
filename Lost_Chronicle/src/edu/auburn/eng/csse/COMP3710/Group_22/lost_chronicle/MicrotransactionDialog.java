package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MicrotransactionDialog extends DialogFragment {
	private StoreCommunicator mCommunicator;
	private Button mContinueButton;
	private TextView mPrice, mItemName;
	
	public MicrotransactionDialog() {
		// TODO Auto-generated constructor stub
	}
	
	public void onAttach(Activity activity) {
		mCommunicator = (StoreCommunicator) activity;
	}
	//Needs the Purchasable object that was clicked
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = this.getArguments();
		Purchasable item = args.getParcelable(Selection_Screen.PURCHASE_KEY);
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.fragment_microtransaction_dialog);
		dialog.setTitle("Mobage Ltd.");
		mPrice = (TextView) dialog.findViewById(R.id.item_cost_text);
		mItemName = (TextView) dialog.findViewById(R.id.item_name_text);
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis() * 127);
		int randomCost = Math.abs(rand.nextInt() % 19 + 1);
		mPrice.setText("$" + randomCost + ".00");
		mItemName.setText(item.getPurchaseName());
		mContinueButton = (Button) dialog.findViewById(R.id.continue_microtransaction_button);
		mContinueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO unlock the purchasable, close dialog
				//At this point you can either send the purchasable to the hosting activity 
				dialog.dismiss();
			}
		});
	return dialog;
	}
}