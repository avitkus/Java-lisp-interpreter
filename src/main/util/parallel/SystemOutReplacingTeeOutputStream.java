package main.util.parallel;

import java.io.PrintStream;

public class SystemOutReplacingTeeOutputStream extends TeeOutputStream {
	public SystemOutReplacingTeeOutputStream() {
		super();
		addStream(System.out);
		System.setOut(new PrintStream(this));
	}
}
