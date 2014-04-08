package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;

public class KanojoStore extends Fragment {
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
		ImageAdapter imageAdapter = new ImageAdapter(this);
		gridView.setAdapter(imageAdapter);
		gridView.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO if kanojo hasn't been bought then display purchase dialog
				// TODO if kanojo is already selected then deselect
				// TODO if kanojo isn't selected then set kanojo as active
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Nothing
			}
		    });
		return v;
		
	}
	@Override
	public void onStart() {
		super.onStart();
	}
	
}
