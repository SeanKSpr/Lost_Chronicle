package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddEventFragment extends Fragment{
	private EditText mTitle, mStartTime, mEndTime, mDescription;
	private Spinner mEventType;
	private RatingBar mDifficulty;
	private EventCommunicator comm;
	private TextView mStartDate, mEndDate;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setCurrentDateOnView(); TODO
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
		
		mStartDate =  (EditText) getView().findViewById(R.id.startDate);
		
		mEndDate =  (EditText) getView().findViewById(R.id.endDate);
		
		mTitle = (EditText) getView().findViewById(R.id.editText1);
		
		//mStartDate = (EditText) getView().findViewById(R.id.tvStartDate);
		
		mStartTime = (EditText) getView().findViewById(R.id.startTime);
		
		//mEndDate = (EditText) getView().findViewById(R.id.tvEndDate);
		
		mEndTime = (EditText) getView().findViewById(R.id.endTime);
		
		mDescription = (EditText) getView().findViewById(R.id.editText6);
		
		mEventType = (Spinner) getView().findViewById(R.id.spinner1);
		
		mDifficulty = (RatingBar) getView().findViewById(R.id.ratingBar1);
		
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				boolean addable = true;
				String title = mTitle.getText().toString();
				if(title.compareTo("") == 0)
				{
					addable = false;
					Toast.makeText(getActivity(), "You must enter a title", Toast.LENGTH_SHORT).show();
				}
				String startDate = mStartDate.getText().toString();
				if(startDate.compareTo("") == 0)
				{
					addable = false;
					Toast.makeText(getActivity(), "You must enter a start date", Toast.LENGTH_SHORT).show();
				}
				String startTime = mStartTime.getText().toString();
				if(startTime.compareTo("") == 0)
				{
					addable = false;
					Toast.makeText(getActivity(), "You must enter a start time", Toast.LENGTH_SHORT).show();
				}
				String endDate = mEndDate.getText().toString();
				if(endDate.compareTo("") == 0)
				{
					addable = false;
					Toast.makeText(getActivity(), "You must enter an end date", Toast.LENGTH_SHORT).show();
				}
				String endTime = mEndTime.getText().toString();
				if(endTime.compareTo("") == 0)
				{
					addable = false;
					Toast.makeText(getActivity(), "You must enter an end time", Toast.LENGTH_SHORT).show();
				}
				String description = mDescription.getText().toString();
				if(description.compareTo("") == 0)
				{
					addable = false;
					Toast.makeText(getActivity(), "You must enter a description", Toast.LENGTH_SHORT).show();
				}
				String type = mEventType.getSelectedItem().toString();
				float difficulty  = mDifficulty.getRating();
				Date startingDate, endingDate;
				startingDate = new Date();
				endingDate = new Date();
				if(addable)
				{
					startingDate = parseStringToDate(startDate, startTime);
					endingDate = parseStringToDate(endDate, endTime);
					Event newEvent = new Event(startingDate, endingDate, title, type, description, difficulty);
					comm.respond(newEvent);
				}
				
				
			}
		});
		mStartDate.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	DialogFragment newFragment = new DatePickerFragment(mStartDate);
	        	Bundle dateArgs = new Bundle();
	        	dateArgs.putString("date", "start");
	        	newFragment.setArguments(dateArgs);
            	newFragment.show(getFragmentManager(), "DatePicker");
	        }

	    });
		mEndDate.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	DialogFragment newFragment = new DatePickerFragment(mEndDate);
	        	Bundle dateArgs = new Bundle();
	        	dateArgs.putString("date", "end");
	        	newFragment.setArguments(dateArgs);
            	newFragment.show(getFragmentManager(), "DatePicker");
	        	
	        }

	    });
		mStartTime.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	DialogFragment newFragment = new TimePickerFragment(mStartTime);
	        	Bundle timeArgs = new Bundle();
	        	timeArgs.putString("time", "start");
	        	newFragment.setArguments(timeArgs);
            	newFragment.show(getFragmentManager(), "TimePicker");
	        }

	    });
		mEndTime.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	DialogFragment newFragment = new TimePickerFragment(mEndTime);
	        	Bundle timeArgs = new Bundle();
	        	timeArgs.putString("time", "end");
	        	newFragment.setArguments(timeArgs);
            	newFragment.show(getFragmentManager(), "TimePicker");
	        }

	    });

		super.onStart();
	}		
	
	public static Date parseStringToDate(String dateDay, String dateTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.US);
		Date date = null;
		try {
			date = formatter.parse(dateDay + " " + dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public void setStartText(String textIn)
	{
		mStartDate.setText(textIn);
	}
}
		
