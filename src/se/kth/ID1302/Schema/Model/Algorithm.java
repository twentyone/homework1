package se.kth.ID1302.Schema.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;

public class Algorithm {

	static final long QUARTER = 900_000;
	static final long HOUR = 3_600_000;
	static final long DAY = 86_400_000;

	private Block[][] blocks;
	List<VEvent> possibleMeetings = new ArrayList<VEvent>();

	public Algorithm(EventTree<Date, Event> tree, Date dateStart, Date dateEnd,
			Date timeStart, Date timeEnd, int duration, int maxUnattendance) {

		long secs = (timeEnd.getTime() - timeStart.getTime()) / 1000;
		int amountBlocksPerDay = (int) ((secs / 3600f) / 0.25);
		duration = duration / 15;
		int amountDays = (int) (((dateEnd.getTime() - dateStart.getTime()) / 1000) / 86400) + 1;
		int dayNumber = 0;
		blocks = new Block[amountDays][amountBlocksPerDay];
		
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				blocks[i][j] = new Block();
			}
		}
		
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
						if (datesOverlapping(e.getStart(), e.getEnd(), from, till)) {
							blocks[dayNumber][block].addToPriority(1);
							blocks[dayNumber][block].addToPeopleNotPresent(e.getOwner());
						}
						startOfDay = till;
					}
				}
			}
			day.setTime(day.getTime() + DAY);
			dayNumber++;
		}

		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				int J = j;
				
				Block event2 = new Block();
				while (J < blocks[i].length) {
					if ((J-j) == duration) {
						Date date = new Date(dateStart.getTime() + DAY * i);
						date.setHours(timeStart.getHours());
						date.setMinutes(timeStart.getMinutes());
						date.setTime(date.getTime() + QUARTER * j);
						
						Date date2 = new Date(date.getTime() + QUARTER * duration);
						DateTime test1 = new DateTime(date);
						DateTime test2 = new DateTime(date2);
						
						test1.setUtc(true);
						test2.setUtc(true);
						
						VEvent event = new VEvent(test1, test2, event2.toString());
						possibleMeetings.add(event);
						break;
					}
					event2.addToPriority(blocks[i][J].getPriority());
					event2.addToPeopleNotPresent(blocks[i][J].getPeopleNotPresent());
					if (event2.getNumberOfNotPresetPersons() > maxUnattendance)
						break;
					J++;
				}
			}
		}
	}

	private static boolean datesOverlapping(Date start1, Date end1,
			Date start2, Date end2) {
		return (((start1 == null) || (end2 == null) || start1.before(end2)) && ((start2 == null)
				|| (end1 == null) || end1.after(start2)));
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
	
	public List<VEvent> getPossibleMeetings() {
		return possibleMeetings;
	}
}
