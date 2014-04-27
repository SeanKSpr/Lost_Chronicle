package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public interface EventCommunicator {
	public void respond(Event newEvent);
	public void addRating(Event event);
	public void updateEvent(float rating);
}
