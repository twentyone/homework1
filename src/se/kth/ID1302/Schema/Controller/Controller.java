package se.kth.ID1302.Schema.Controller;

import java.text.ParseException;
import java.util.Date;



import se.kth.ID1302.Schema.Model.EventTree;
import se.kth.ID1302.Schema.Model.Importer;
import se.kth.ID1302.Schema.Model.Event;
import se.kth.ID1302.Schema.Model.Algorithm;

public class Controller {
	
	public void importSchema(EventTree<Date, Event> tree, String owner, String nameOfFile) throws ParseException {
		Importer.importCalendar(tree, owner, nameOfFile);
	}
	
	public void runAlgorithm(EventTree<Date, Event> tree, 
										 Date dateStart, Date dateEnd, 
										 Date timeStart, Date timeEnd,int duration) {
		Algorithm.runAlgorithm(tree, dateStart, dateEnd, timeStart, timeEnd,duration);
	}
}
