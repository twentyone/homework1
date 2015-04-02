package se.kth.ID1302.Schema.Controller;

import java.text.ParseException;
import java.util.Date;

import se.kth.ID1302.Schema.Model.EventTree;
import se.kth.ID1302.Schema.Model.Importer;
import se.kth.ID1302.Schema.Model.Event;

public class Controller {
	
	public void importSchema(EventTree<Date, Event> tree, String owner) throws ParseException {
		Importer.importCalendar(tree, owner);
	}
}
