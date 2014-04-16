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
		String itemName = args.getString(KanojoStore.KANOJO_NAME_KEY);
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.fragment_microtransaction_dialog);
		dialog.setTitle("Mobage Ltd.");
		mPrice = (TextView) dialog.findViewById(R.id.item_cost_text);
		mItemName = (TextView) dialog.findViewById(R.id.item_name_text);
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis() * 127);
		int randomCost = Math.abs(rand.nextInt() % 19 + 1);
		mPrice.setText("$" + randomCost + ".00");
		mItemName.setText(itemName);
		mContinueButton = (Button) dialog.findViewById(R.id.continue_microtransaction_button);
		mContinueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO unlock the kanojo, close dialog
				dialog.dismiss();
			}
		});
	return dialog;
	}
}