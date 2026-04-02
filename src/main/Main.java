package main;

import java.nio.file.*;
import java.util.*;
import java.io.IOException;

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

		if (args.length >= 2 && args[0].equals("write")) {
			System.out.printf("Writing trace to %s...\n", args[1]);
			write(Paths.get(args[1]), trace.map());
		}
	}

	public static void write(Path path, Map<String, Collection<String>> traceMap) {
		ArrayList<String> lines = new ArrayList<String>();
		for (String k : traceMap.keySet()) {
			lines.add(k);
			for (String v : traceMap.get(k)) {
				lines.add(String.format("\t%s", v));
			}
		}

		try {
			Files.write(path, lines);
			System.out.println("Trace written successfully.");
		} catch (IOException e) {
			System.out.printf("Error writing file:\n%s", e.toString());
		}
	}
}
