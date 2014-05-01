package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	
	private  TextView dateTextView;
	private String activityDateArgs;

public DatePickerFragment(TextView dateView)
{
	dateTextView = dateView;
}

@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
	Bundle b = getArguments();
	activityDateArgs = b.getString("date");
	

    final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
    	if(activityDateArgs.equals("start"))
    	{
    		dateTextView.setText(String.valueOf(month + 1) + "/" +   String.valueOf(day) + "/" + String.valueOf(year));
    	}
    	else if(activityDateArgs.equals("end"))
    	{
    		dateTextView.setText(String.valueOf(month + 1) + "/" +   String.valueOf(day) + "/" + String.valueOf(year));    	}
    }
}

