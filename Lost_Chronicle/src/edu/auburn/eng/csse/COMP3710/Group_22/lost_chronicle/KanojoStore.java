package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class KanojoStore extends Fragment {
	StoreCommunicator mStoreCommunicator;
	Purchasable chosenKanojo;
	final KanojoStore kanojoStore = this;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mStoreCommunicator = (StoreCommunicator) this.getActivity();
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_kanojo_store_screen, container, false);
		GridView gridView = (GridView) v.findViewById(R.id.kanojo_store);
		ArrayList<Purchasable> purchasableArray = mStoreCommunicator.getPurchasables();
		PurchasableAdapter purchasableAdapter = new PurchasableAdapter(this, purchasableArray);
		gridView.setAdapter(purchasableAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				chosenKanojo = (Purchasable) parent.getItemAtPosition(position);
				if (chosenKanojo.hasBeenPurchased()) {
					kanojoStore.popStore();
					mStoreCommunicator.updatePurchasable(chosenKanojo);
				}
				else {
					PurchaseDialogFragment purchaseDialog = new PurchaseDialogFragment();
					Bundle args = new Bundle();
					args.putParcelable(Selection_Screen.PURCHASE_KEY, chosenKanojo);
					purchaseDialog.setArguments(args);
					getActivity().getFragmentManager().beginTransaction()
					.add(purchaseDialog, PurchaseDialogFragment.PURCHASE_DIALOG_KEY).commit();
					//if the previous tier hasn't been unlocked then it doesn't display the choice to pay in gold
				}
			}
			
		});
		return v;
		
	}
	protected void popStore() {
		this.getFragmentManager().popBackStackImmediate();	
	}
	@Override
	public void onStart() {
		super.onStart();
	}
}
