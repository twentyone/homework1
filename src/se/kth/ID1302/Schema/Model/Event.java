package se.kth.ID1302.Schema.Model;

import java.util.Date;

public class Event {
	private String id;
	private Date start;
	private Date end;
	
	public Event(Date start, Date end, String id) {
		this.id = id;
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
}
