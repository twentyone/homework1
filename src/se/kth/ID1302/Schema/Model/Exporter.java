package se.kth.ID1302.Schema.Model;

import java.util.List;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.Calendar;

public class Exporter {
	
	public static void createCalender(List<VEvent> possibleMeetings) {
		Calendar icsCalendar = new Calendar();
		icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
		icsCalendar.getProperties().add(CalScale.GREGORIAN);

		// Add the event and print
		for (VEvent e : possibleMeetings) {
			icsCalendar.getComponents().add(e);
		}

		System.out.println(icsCalendar);
		
	}

}
