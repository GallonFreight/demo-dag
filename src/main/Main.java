package main;

public class Main {

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
