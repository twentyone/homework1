package se.kth.ID1302.Schema.Model;

import java.util.Date;

public class Event {
	private String id;
	private String owner;
	private Date start;
	private Date end;
	
	public Event(Date start, Date end, String id, String owner) {
		this.id = id;
		this.owner = owner;
		this.start = start;
		this.end = end;
	}

	public String getId() {
		return id;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}
	
	public String getOwner() {
		return owner;
	}

	public String toString() {
		return "Owner: " + owner + "\nUID: " + id + "\nStart: " + start + "\nEnd: " + end;
	}
}
