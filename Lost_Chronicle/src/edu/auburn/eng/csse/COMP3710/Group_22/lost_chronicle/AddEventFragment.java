package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class AddEventFragment extends Fragment {
	EditText mTitle, mStartDate, mStartTime, mEndDate, mEndTime, mDescription;
	Spinner mEventType;
	RatingBar mDifficulty;
	EventCommunicator comm;
	final AddEventFragment addEventFragment = this;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onAttach(Activity activity) {
		comm = (EventCommunicator) activity;
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_add_event, container, false);
		return v;
		
	}
	
	@Override
	public void onStart() {
		Button addButton = (Button) getView().findViewById(R.id.button1);
		
		mTitle = (EditText) getView().findViewById(R.id.editText1);
		
		mStartDate = (EditText) getView().findViewById(R.id.editText2);
		
		mStartTime = (EditText) getView().findViewById(R.id.editText3);
		
		mEndDate = (EditText) getView().findViewById(R.id.editText4);
		
		mEndTime = (EditText) getView().findViewById(R.id.editText5);
		
		mDescription = (EditText) getView().findViewById(R.id.editText6);
		
		mEventType = (Spinner) getView().findViewById(R.id.spinner1);
		
		mDifficulty = (RatingBar) getView().findViewById(R.id.ratingBar1);
		
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				String title = mTitle.getText().toString();
				String startDate = mStartDate.getText().toString();
				String startTime = mStartTime.getText().toString();
				String endDate = mEndDate.getText().toString();
				String endTime = mEndTime.getText().toString();
				String description = mDescription.getText().toString();
				String type = mEventType.getSelectedItem().toString();
				float difficulty  = mDifficulty.getRating();
				Date startingDate, endingDate;
				startingDate = new Date();
				endingDate = new Date();
				startingDate = parseStringToDate(startDate, startTime);
				endingDate = parseStringToDate(endDate, endTime);
				Event newEvent = new Event(startingDate, endingDate, title, type, description, difficulty);
				comm.respond(newEvent);
				
				
			}
		});
		super.onStart();
	}
	public static Date parseStringToDate(String dateDay, String dateTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd hh:mm", Locale.US);
		Date date = null;
		try {
			date = formatter.parse(dateDay + " " + dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
		
