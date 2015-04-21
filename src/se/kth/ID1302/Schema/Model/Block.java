package se.kth.ID1302.Schema.Model;

import java.util.ArrayList;
import java.util.List;

public class Block {
	private int priority;
	private List<String> peopleNotPresent;

	public Block(int numberOfPeopleNotPresent, List<String> peopleNotPresent) {
		this.priority = numberOfPeopleNotPresent;
		this.peopleNotPresent = peopleNotPresent;
	}

	public Block() {
		this.priority = 0;
		this.peopleNotPresent = new ArrayList<String>();
	}

	public int getPriority() {
		return priority;
	}

	public void addToPriority(int add) {
		this.priority += add;
	}

	public List<String> getPeopleNotPresent() {
		return peopleNotPresent;
	}

	public void addToPeopleNotPresent(String personNotPresent) {
		for (String s : this.peopleNotPresent) {
			if (s.equals(personNotPresent))
				return;
		}
		this.peopleNotPresent.add(personNotPresent);
	}

	public void addToPeopleNotPresent(List<String> personNotPresent) {
		for (String s : personNotPresent) {
			this.addToPeopleNotPresent(s);
		}
	}
	
	public int getNumberOfNotPresetPersons() {
		return this.peopleNotPresent.size();
	}
	
	public String toString() {
		if (this.priority > 0) {
			StringBuilder sb = new StringBuilder("Prio: " + priority + " - People: " );
			for (String s : peopleNotPresent) {
				sb.append(s + ", ");
			}
			
			return sb.toString();
		} else
			return "";
	}
}
