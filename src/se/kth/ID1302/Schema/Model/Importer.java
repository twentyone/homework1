package se.kth.ID1302.Schema.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;



import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.parameter.Value;

public class Importer {
	
	public static void importCalendar(EventTree<Date, Event> eventTree, String owner, String nameOfFile) throws ParseException {
		FileInputStream fin;
		CalendarBuilder builder;
		Calendar calendar = null;
		
		try {
			//TODO: get file
			fin = new FileInputStream(nameOfFile);
			builder = new CalendarBuilder();
			calendar = builder.build(fin);
			
		
		} catch (ParserException e) {
			System.err.println("Parse Exception");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot read the file");
		} catch (IOException e) {
			System.err.println("I/O Exception");
		}
		
		for (Object c : calendar.getComponents()) {
		    Component component = (Component) c;
		    
		    String start = component.getProperties().getProperty(Property.DTSTART).getValue();
		    String end   = component.getProperties().getProperty(Property.DTEND).getValue();
		    String uid   = component.getProperties().getProperty(Property.UID).getValue();
		    
		    DateFormat test = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		  	Date startDate  = test.parse(start);
		    Date endDate    = test.parse(end);
		    
		    //Kan tänka mig att det räcker med en enda Long här istället för 3 ints? YYYYMMDD
		    Date key = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate());
		    
		    Event event = new Event(startDate, endDate, uid, owner);

		    eventTree.put(key, event);
		    
		}
		
	}

}
