package main;

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

	public static void main(String[] args) {
		System.out.println("Begin contact tracing...\n");
		ContactTrace trace = new ContactTrace();

		trace.logContact("Agnes", "Tom");
		trace.logContact("Jim", "Agnes");
		trace.logContact("Louise", "Jim");
		trace.logContact("Tom", "Marie");
		trace.logContact("Pierre", "Sheila");
		trace.logContact("Francois", "Pierre");
		trace.logContact("Jaques", "Pierre");
		trace.logContact("Sue", "Tom");
		trace.logContact("Millie", "Tom");
		trace.logContact("Billie", "Tom");
		trace.logContact("Gillie", "Tom");
		trace.logContact("Willie", "Tom");
		trace.logContact("Tom", "YOU!");
		System.out.println();

		trace.printGivers("Tom");
		trace.printGetters("Tom");

		System.out.println("Contact tracing complete.");
	}
}
