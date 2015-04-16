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

	static final long QUARTER = 900_000;
	static final long HOUR = 3_600_000;
	static final long DAY = 86_400_000;

	private int[][] blocks;

	public Algorithm(EventTree<Date, Event> tree, Date dateStart, Date dateEnd,
			Date timeStart, Date timeEnd) {

		long secs = (timeEnd.getTime() - timeStart.getTime()) / 1000;
		int amountBlocksPerDay = (int) ((secs / 3600f) / 0.25);
		
		int amountDays = (int) (((dateEnd.getTime() - dateStart.getTime()) / 1000) / 86400) + 1;
		int dayNumber = 0;
		blocks = new int[amountDays][amountBlocksPerDay];

		
		Date day = (Date) dateStart.clone();
		while (day.before(dateEnd) || day.equals(dateEnd)) {
			List<Event> events = tree.get(day);
			if (events != null) {
				for (Event e : events) {
					Date startOfDay = new Date(day.getYear(), day.getMonth(),
							day.getDate(), timeStart.getHours(),
							timeStart.getMinutes());
					for (int block = 0; block < amountBlocksPerDay; block++) {
						Date from = startOfDay;
						Date till = new Date(startOfDay.getTime() + QUARTER);
						if (datesOverlapping(e.getStart(), e.getEnd(), from,
								till)) {
							blocks[dayNumber][block] = blocks[dayNumber][block] + 1;
						}
						startOfDay = till;
					}
				}
			}
			day.setTime(day.getTime() + DAY);
			dayNumber++;
		}
	}
	// TRUE om Överlappning existerar, false om de inte överlappar
	private static boolean datesOverlapping(Date start1, Date end1,
			Date start2, Date end2) {
		return (((start1 == null) || (end2 == null) || start1.before(end2)) && ((start2 == null)
				|| (end1 == null) || end1.after(start2)));
	}
	
	public int[][] getBlocks(){
		return blocks;
	}
}
