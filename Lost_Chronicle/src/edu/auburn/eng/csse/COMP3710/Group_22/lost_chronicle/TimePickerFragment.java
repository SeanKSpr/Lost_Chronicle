package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	
	public TextView timeTextView;
	private String activityTimeArgs;
	
	public TimePickerFragment(TextView timeView)
	{
		timeTextView = timeView;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle b = getArguments();
		activityTimeArgs = b.getString("time");
		

	    final Calendar c = Calendar.getInstance();
	        int hour = c.get(Calendar.HOUR_OF_DAY);
	        int minute = c.get(Calendar.MINUTE);
	        

	        return new TimePickerDialog(getActivity(), this, hour, minute,
	                DateFormat.is24HourFormat(getActivity()));
	    }

	public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) 
	{
//		if(selectedHour > 12)
//		{
//			selectedHour -= 12;
//		}
		if(activityTimeArgs.equals("start"))
    	{
			if(selectedMinute == 0)
			{
				timeTextView.setText( selectedHour + ":" + selectedMinute + "0");
			}
			else
			{
				timeTextView.setText( selectedHour + ":" + selectedMinute);
				
			}
    	}
		else if(activityTimeArgs.equals("end"))
    	{
			if(selectedMinute == 0)
			{
				timeTextView.setText( selectedHour + ":" + selectedMinute + "0");
			}
			else
			{
				timeTextView.setText( selectedHour + ":" + selectedMinute);
				
			}    	}
	}

}
