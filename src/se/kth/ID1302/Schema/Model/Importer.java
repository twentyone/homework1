package se.kth.ID1302.Schema.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

public class Importer {

	public static void main(String[] args) {
		
		FileInputStream fin;
		CalendarBuilder builder;
		Calendar calendar = null;
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
		
		
		for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
		    Component component = (Component) i.next();
		    System.out.println("Component [" + component.getName() + "]");

		    for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
		        Property property = (Property) j.next();
		        System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
		    }
		}
		
		
	}

}
