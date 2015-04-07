package se.kth.ID1302.Schema.Model;

import java.util.Date;
import java.util.List;

public class Algorithm {

	public static void runAlgorithm(EventTree<Date, Event> tree, 
			Date dateStart, Date dateEnd, 
			Date timeStart, Date timeEnd) {
	
		double hours = (timeEnd.getHours()   + timeEnd.getMinutes()   / 60f) - 
					   (timeStart.getHours() + timeStart.getMinutes() / 60f);
		
		System.out.println(hours / 0.25);
//		
//		int[][] blocks;
//		
//		// Varje dag
//		for(Date d : tree.keys(dateStart, dateEnd)) {
//			List<Event> events = tree.get(d);
//			
//			// Varje event
//			for(Event e : events) {
//				
//			}
//			
//		}
		
	}

	private static boolean datesOverlapping(Date start1, Date end1, Date start2, Date end2) {
		return (((start1 == null) || (end2 == null) || start1.before(end2)) &&
				((start2 == null) || (end1 == null) || end1.after(start2)));
	}
}
