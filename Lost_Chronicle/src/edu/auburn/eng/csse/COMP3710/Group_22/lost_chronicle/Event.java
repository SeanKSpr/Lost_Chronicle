package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Date;
public class Event {

	private Date start_time, end_time;
	private String title, description, type;
	private short on_going = 0;
	private int _id;
	private int lengthInMinutes;
	private float eval;
	private float difficulty;
	
	
	public int getLengthInMinutes() {
		long diffInMillies = end_time.getTime() - start_time.getTime();
		lengthInMinutes = (int) diffInMillies / 60000;
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Event(){}
	
	public Event(Date startDateIn, Date endDateIn, String titleIn, String typeIn, String descriptionIn, float difficultyIn)
	{
		start_time = startDateIn;
		end_time = endDateIn;
		title = titleIn;
		description = descriptionIn;
		type = typeIn;
		difficulty = difficultyIn;
		on_going = 1;
	}
	
	public String timeConflicts()
	{
		String conflict = null;
		if(start_time.before(new Date()))
		{
			conflict = "Events cannot be scheduled in the past.";
		}
		else if(end_time.before(new Date()))
		{
			conflict = "Events cannot be scheduled in the past.";
		}
		else if(start_time.after(end_time))
		{
			conflict = "An event cannot end before it began";
		}
		return conflict;
	}
	
	public String timeConflicts(Event eventIn)
	{
		String conflict = null;
		boolean startsAfterOtherBegan = start_time.after(eventIn.getStart_time());
		boolean endsBeforeOtherEnds = end_time.before(eventIn.getEnd_time());
		if(startsAfterOtherBegan && endsBeforeOtherEnds)
		{
			conflict = "You cannot schedule concurrent events";
		}
		else if(start_time.before(new Date()))
		{
			conflict = "Events cannot be scheduled in the past.";
		}
		else if(end_time.before(new Date()))
		{
			conflict = "Events cannot be scheduled in the past.";
		}
		else if(start_time.after(end_time))
		{
			conflict = "An event cannot end before it began";
		}
		return conflict;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public short isOnGoing() {
		return on_going;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(float difficulty) {
		this.difficulty = difficulty;
	}

	public float getEval() {
		return eval;
	}

	public void setEval(float eval) {
		this.eval = eval;
	}

	public void setId(int i) {
		this._id = i;
		
	}

	public int getId() {
		return _id;
	}

	public void setOnGoing(short i) {
		this.on_going = i;		
	}
	
}
