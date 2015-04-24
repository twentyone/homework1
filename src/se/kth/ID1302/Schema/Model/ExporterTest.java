package se.kth.ID1302.Schema.Model;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;

import org.junit.Test;

/**
 * 
 * Testa om export av resultat från algoritmen till en fil fungerar, genom att kolla om fillen finns 
 * samt att filens skapas med 0 event och med 1 event.
 * 
 * @author Grupp 1 
 *
 */

public class ExporterTest {

	@Test
	public void testCreateCalender() {
		List<VEvent> possibleMeetings = new ArrayList<VEvent>();
		
		Exporter.createCalender(possibleMeetings, "exportedEvent.ics");
		
		File f = new File("exportedEvent.ics");
		assertTrue(f.exists());

		EventTree<Date, Event> eventTree = new EventTree<Date, Event>();
		Importer.importCalendar(eventTree, "", "exportedEvent.ics");
		assertEquals(0, eventTree.size());
		f.delete();

		DateTime start = new DateTime(new Date(0,0,0,8,0));
		DateTime end   = new DateTime(new Date(0,0,0,8,30));
		start.setUtc(true);
		end.setUtc(true);
		possibleMeetings.add(new VEvent(start, end, ""));
		
		Exporter.createCalender(possibleMeetings, "exportedEvent.ics");
		Importer.importCalendar(eventTree, "", "exportedEvent.ics");
		assertEquals(1, eventTree.size());

		f.delete();
		assertTrue(!f.exists());
	}

}
