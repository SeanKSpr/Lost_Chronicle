package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Date;
public class Event {

	private Date start_time, end_time;
	private String title, description, type;
	private float eval;
	private float difficulty;

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


	
	Event(Date startDateIn, Date endDateIn, String titleIn, String typeIn, String descriptionIn, float difficultyIn)
	{
		start_time = startDateIn;
		end_time = endDateIn;
		title = titleIn;
		description = descriptionIn;
		type = typeIn;
		difficulty = difficultyIn;
	}
	
	boolean timeConflicts(Event eventIn)
	{
		if(start_time.after(eventIn.getStart_time()) && end_time.before(eventIn.getEnd_time()))
		{
			return true;
		}
		return false;
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

	public String isOnGoing() {
		// TODO Auto-generated method stub
		return null;
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

	public void setId(int parseInt) {
		// TODO Auto-generated method stub
		
	}
	
}
