package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class KanojoStore extends Fragment {
	//private String mClickedCompanion;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_kanojo_store_screen, container, false);
		GridView gridView = (GridView) v.findViewById(R.id.kanojo_store);
		PurchasableAdapter imageAdapter = new PurchasableAdapter(this);
		gridView.setAdapter(imageAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
				//mClickedCompanion = (String) parent.getItemAtPosition(position);
				PurchaseDialogFragment purchaseDialog = new PurchaseDialogFragment();
				getActivity().getFragmentManager().beginTransaction().add(purchaseDialog, "temp").commit();
				//needs to launch DialogFragment which displays the money type selection thing and price for unlock
				//if the previous tier hasn't been unlocked then it doesn't display the choice to pay in gold
				
			}
			
		});
		return v;
		
	}
	@Override
	public void onStart() {
		super.onStart();
	}
	
}
