package main;

import java.util.*;
import me.alexjs.dag.*;

public class ContactTrace {

	private Dag<String> dag;

	public ContactTrace() {
		dag = new HashDag<String>();
	}

	public void logContact(String from, String to) {
		dag.put(from, to);
		System.out.printf("%s got it from %s\n", to, from);
	}

	public void printGivers(String getter) {
		System.out.printf("%s got it from:\n", getter);
		for (String giver : dag.getIncoming(getter)) {
			System.out.printf("\t%s\n", giver);
		}
		System.out.println();
	}

	public void printGetters(String giver) {
		System.out.printf("%s gave it to:\n", giver);
		for (String getter : dag.getOutgoing(giver)) {
			System.out.printf("\t%s\n", getter);
		}
		System.out.println();
	}

	public Map<String, Collection<String>> map() {
		return dag.toMap();
	}
}
