package main;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;
import me.alexjs.dag.*;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			demoTrace();
			return;
		}
		
		if (args.length >= 2 && args[0].equals("write")) {
			write(Paths.get(args[1]), demoTrace().map());
			return;
		}

		if (args.length >= 3 && args[0].equals("read")) {
			read(Paths.get(args[1]), args[2]);
			return;
		}
	}

	public static void read(Path path, String name) {
		System.out.printf("Reading trace from %s ...\n", path.toString());

		List<String> lines;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.printf("Error reading file:\n%s", e.toString());
			return;
		}

		Dag<String> dag = new HashDag<String>();
		String source = null;
		for (String line : lines) {
			if (line.charAt(0) == '\t') {
				dag.put(source, line.substring(1));
			} else {
				source = line;
				dag.putAll(source, Collections.emptyList());
			}
		}
		ContactTrace trace = new ContactTrace(dag);
		System.out.println("Finished reading trace.\n");

		System.out.printf("Tracing contacts with %s ...\n", name);
		trace.printGivers(name);
		trace.printGetters(name);
		System.out.println("Finished.");
	}

	public static void write(Path path, Map<String, Collection<String>> traceMap) {
		System.out.printf("Writing trace to %s ...\n", path.toString());
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

	public static ContactTrace demoTrace() {
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
		return trace;
	}
}
