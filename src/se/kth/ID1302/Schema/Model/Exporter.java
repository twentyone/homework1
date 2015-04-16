package se.kth.ID1302.Schema.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;

public class Exporter {
	
	public void exportToIcal(Algorithm alg, Date dateStart, Date dateEnd, 
			 Date timeStart, Date timeEnd,int duration) {
		
		int [][] blocks = alg.getBlocks();
		duration = duration / 15;

		List<VEvent> possibleMeetings = new ArrayList<VEvent>();
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (blocks[i][j] == 0) {
					int J = j;
					
					int numberOfZeros = 0;
					while (J < blocks[i].length && blocks[i][J] == 0) {
						numberOfZeros++;
						if (numberOfZeros == duration) {
							Date date = new Date(dateStart.getTime() + alg.DAY * i);
							date.setHours(timeStart.getHours());
							date.setMinutes(timeStart.getMinutes());
							date.setTime(date.getTime() + alg.QUARTER * j);
							Date date2 = new Date(date.getTime() + alg.QUARTER * duration);

							DateTime test1 = new DateTime(date);
							DateTime test2 = new DateTime(date2);

							test1.setUtc(true);
							test2.setUtc(true);
							VEvent event = new VEvent(test1, test2, "test");
							possibleMeetings.add(event);
						}
						J++;
					}
				}
			}

		}
		System.out.println(possibleMeetings.size());
		createCalender(possibleMeetings);
	}
	
	
	private static void createCalender(List<VEvent> possibleMeetings) {
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
