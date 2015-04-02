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

	public static void main(String[] args) {
		try {
			EventTree<String, Event> tree = new EventTree<String, Event>();
			new Importer().importCalendar(tree);
				System.out.println(tree.get("115012"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void importCalendar(EventTree<String, Event> eventTree) throws ParseException {
		FileInputStream fin;
		CalendarBuilder builder;
		Calendar calendar = null;
		//asd
		
		try {
			fin = new FileInputStream("personal.ics");
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
		    
		    Event event = new Event(startDate, endDate, uid);
		    
		    String key = Integer.toString(startDate.getYear()) + 
		    			 Integer.toString(startDate.getMonth()) + 
    					 Integer.toString(startDate.getDate());

		    eventTree.put(key, event);
		    
//		    String dateTime = component.getProperties().getProperty(Property.DTSTART).getValue();
//		    System.out.println(dateTime);
//		    DateFormat test = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
//		  	test.parse(dateTime);
		  
		    
		    
//		    for (Object p : component.getProperties()) {
//		        Property property = (Property) p;
//		    	if (property.equals(Property.)) {
//		    		
//		    	}
//		    		// lägg in i ds (property.getValue()) 
//		    }
		}
//		
//		for (Iterator i = calendar1.getComponents().iterator(); i.hasNext();) {
//		    Component component = (Component) i.next();
//		    System.out.println("Component [" + component.getName() + "]");
//
//		    for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
//		        Property property = (Property) j.next();
//		        System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
//		    }
//		}
		
		
	}

}
