package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

public class EventCompletionFragment extends Fragment{

	private Button mSubmitButton;
	private RatingBar mRatingBar;
	private EventCommunicator comm;
	private EventCompletionFragment thisFragment = this;
	public EventCompletionFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.event_completion_dialog, container, false);
		mSubmitButton = (Button) v.findViewById(R.id.completionSubmitButton);
		mRatingBar = (RatingBar) v.findViewById(R.id.completionRatingBar);
		return v;
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		comm = (EventCommunicator)activity;
	}

	@Override
	public void onStart() {
		super.onStart();
		mSubmitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				comm.updateEvent(mRatingBar.getRating());
				FragmentManager fm = thisFragment.getActivity().getFragmentManager();
				fm.popBackStackImmediate();
			}
		});
		
		
		
	}
	
	

}
