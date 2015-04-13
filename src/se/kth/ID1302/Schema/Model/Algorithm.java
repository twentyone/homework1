package se.kth.ID1302.Schema.Model;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;

public class Algorithm {

	public static void runAlgorithm(EventTree<Date, Event> tree,
			Date dateStart, Date dateEnd, Date timeStart, Date timeEnd,int duration){

		long secs = (timeEnd.getTime() - timeStart.getTime()) / 1000;
		int amountBlocksPerDay = (int) ((secs / 3600f) / 0.25);
		duration = duration / 15;
		int amountDays = (int) (((dateEnd.getTime() - dateStart.getTime()) / 1000) / 86400) + 1;
		
//		System.out.println(amountDays);
//		
//		System.out.println("Antal tidsblockar(15 min) inom angiven intervall: "+ amountBlocksPerDay);

		int[][] blocks = new int[amountDays][amountBlocksPerDay];

		// Varje dag
		int dayNumber = 0;
		Date day = (Date)dateStart.clone();
		while (day.before(dateEnd) || day.equals(dateEnd)) {
			List<Event> events = tree.get(day);
			if (events != null) {
				for (Event e : events) {
					Date startOfDay = new Date(day.getYear(), day.getMonth(),
							day.getDate(), timeStart.getHours(),
							timeStart.getMinutes());
					for (int block = 0; block < amountBlocksPerDay; block++) {
						Date from = startOfDay;
						Date till = new Date(startOfDay.getTime() + 900_000);
						if (datesOverlapping(e.getStart(), e.getEnd(), from,
								till)) {
							blocks[dayNumber][block] = blocks[dayNumber][block] + 1;
						}
						startOfDay = till;
					}
				}
			}
			day.setTime(day.getTime() + 86_400_000);
			dayNumber++;
		}
		
		for (int i = 0; i<blocks.length;i++){
			
			for(int j = 0; j<blocks[i].length; j++){
				System.out.print(blocks[i][j]);
			}
			System.out.println();
		}
			
		
		List <VEvent> possibleMeetings = new ArrayList <VEvent>();
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
			   if(blocks[i][j] == 0) {
				   int I = i;
				   int J = j;
				   int tryHarder = 0;
				   
				   while (blocks[I][J++] == 0 && 
						  J < blocks[i].length) {
					   tryHarder++;
					   if (tryHarder == duration) {
						   Date date = new Date(dateStart.getTime()+86_400_000*i);
						   //System.out.println(dateStart);
						   date.setHours(timeStart.getHours());
						   date.setMinutes(timeStart.getMinutes());
						   date.setTime(date.getTime()+900_000*j);
						   Date date2 = new Date(date.getTime()+900_000*duration);
						
						   net.fortuna.ical4j.model.DateTime test1 = new net.fortuna.ical4j.model.DateTime(date);
						   net.fortuna.ical4j.model.DateTime test2 = new net.fortuna.ical4j.model.DateTime(date2);

						   test1.setUtc(true);
						   test1.setTime(test1.getTime() + 3_600_000);
						   test2.setUtc(true);
						   test2.setTime(test2.getTime() + 3_600_000);
						   VEvent event = new VEvent(test1, test2, "test");
						   possibleMeetings.add(event);
					   }
				   }
			   }
			}
			
		}
		createCalender(possibleMeetings);
		
	}
	
	private static void createCalender(List <VEvent> possibleMeetings) {
		net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
		icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
		icsCalendar.getProperties().add(CalScale.GREGORIAN);

		// Add the event and print
		for(VEvent e : possibleMeetings) {
			icsCalendar.getComponents().add(e);
			}
	
		System.out.println(icsCalendar);
	}

	// TRUE om Överlappning existerar, false om de inte överlappar
	private static boolean datesOverlapping(Date start1, Date end1, Date start2, Date end2) {
				return (((start1 == null) || (end2 == null) || start1.before(end2)) &&
						((start2 == null) || (end1 == null) || end1.after(start2)));
	}
}
