package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

//Author: Sean Walker
//Build: 1.0
//Date: 4/17/2014
public class Event {
	
	private int id;
	private short onGoing, difficulty, eval;
	private String title, type, start_time, end_time, description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short isOnGoing() {
		return onGoing;
	}

	public void setOnGoing(short onGoing) {
		this.onGoing = onGoing;
	}

	public short getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(short difficulty) {
		this.difficulty = difficulty;
	}

	public short getEval() {
		return eval;
	}

	public void setEval(short eval) {
		this.eval = eval;
	}

	
	public Event(){}
	
	public Event(String title, String type, String start_time, 
			String end_time, short onGoing, short difficulty,
			short eval, String description) {
				super();
				this.title = title;
				this.type = type;
				this.start_time = start_time;
				this.end_time = end_time;
				this.onGoing = onGoing;
				this.difficulty = difficulty;
				this.eval = eval;
				this.description = description;
	}
	
	//getters & setters
	
	
	@Override
	public String toString() {
		return "Event [id=" +id + ", title=" + title + ", type=" + type
				+ ", start time=" + start_time + ", end time=" + end_time 
				+ ", is ongoing=" + ", difficulty=" + difficulty 
				+ ", eval=" + eval + ", description=" + description + "]";
	}

}
