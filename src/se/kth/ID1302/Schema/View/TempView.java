package se.kth.ID1302.Schema.View;

import java.text.ParseException;
import java.util.Date;



import se.kth.ID1302.Schema.Controller.Controller;
import se.kth.ID1302.Schema.Model.Event;
import se.kth.ID1302.Schema.Model.EventTree;

public class TempView {

	public static void main(String[] args) {
		try {
			Controller controller = new Controller();
			EventTree<Date, Event> tree = new EventTree<Date, Event>();
			
			controller.importSchema(tree, "Daniel", "personal.ics");
			controller.importSchema(tree, "Konstantin","personal2.ics");
			
			int duration = 60;
			//controller.importSchema(tree, "Max");
			// Year(Any) Month(1-12) Day(1-31) Hour(0-23) Min(0-59) Sec MilliS
			
			Date test = new Date(0,0,0,8,0);
			
//			test.setTime(test.getTime() - 3_600_000);
			
			Date test2 = new Date(0,0,0,17,0);
//			test.setTime(test.getTime()- 3_600_000);
			
			controller.runAlgorithm(tree, 
					new Date(2015 - 1900, 1 - 1, 6),new Date(2015 - 1900, 1 - 1, 10), 
					test, test2, duration);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
