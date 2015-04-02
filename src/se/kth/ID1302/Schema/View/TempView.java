package se.kth.ID1302.Schema.View;

import java.text.ParseException;
import java.util.Date;

import se.kth.ID1302.Schema.Controller.Controller;
import se.kth.ID1302.Schema.Model.Event;
import se.kth.ID1302.Schema.Model.EventTree;
import se.kth.ID1302.Schema.Model.Importer;

public class TempView {

	public static void main(String[] args) {
		try {
			Controller controller = new Controller();
			EventTree<Date, Event> tree = new EventTree<Date, Event>();
			controller.importSchema(tree, "Daniel");
			controller.importSchema(tree, "Konstantin");
			controller.importSchema(tree, "Max");
			for(Date d : tree.keys(new Date(2015 - 1900, 1 - 1, 8), new Date(2015 - 1900, 1 - 1, 10)))
				System.out.println(tree.get(d));
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
