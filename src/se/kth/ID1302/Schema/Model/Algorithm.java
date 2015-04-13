package se.kth.ID1302.Schema.Model;

import java.util.Date;
import java.util.List;



public class Algorithm {

	public static void runAlgorithm(EventTree<Date, Event> tree,
			Date dateStart, Date dateEnd, Date timeStart, Date timeEnd) {

		long secs = (timeEnd.getTime() - timeStart.getTime()) / 1000;
		int amountBlocksPerDay = (int) ((secs / 3600f) / 0.25);
		

		int amountDays = (int) (((dateEnd.getTime() - dateStart.getTime()) / 1000) / 86400) + 1;
		
		System.out.println(amountDays);
		
		System.out.println("Antal tidsblockar(15 min) inom angiven intervall: "+ amountBlocksPerDay);

		int[][] blocks = new int[amountDays][amountBlocksPerDay];

		// Varje dag
		int dayNumber = 0;
		Date day = dateStart;
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

		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				System.out.print(blocks[i][j] + " ");
			}
			System.out.println();
		}
	}

	// TRUE om Överlappning existerar, false om de inte överlappar
	private static boolean datesOverlapping(Date start1, Date end1, Date start2, Date end2) {
				return (((start1 == null) || (end2 == null) || start1.before(end2)) &&
						((start2 == null) || (end1 == null) || end1.after(start2)));
	}
}
