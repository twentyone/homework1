package se.kth.ID1302.Schema.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.princeton.cs.introcs.StdOut;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;

public class Importer {

	public static void main(String[] args) {
		
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
		    System.out.println(component.getName());
		    String dateTime = component.getProperties().getProperty(Property.DTSTART).getValue();
		    System.out.println(dateTime);
		    System.out.println(DateTime.parse(dateTime));
		    
		    
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
