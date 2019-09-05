package main.lisp.interpreter;

import java.util.Scanner;

/**
 * <p>The {@code BasicLispInterpreterController} class creates a controller for
 * the lisp interpreter under the MVC framework. It provides lines to the
 * model ({@link ObservableLispInterpreter}) and handles registering the view
 * ({@link BasicLispInterpreterView}).
 * 
 * @author Andrew Vitkus
 * 
 * @see BasicLispInterpreterView
 * @see ObservableLispInterpreter
 *
 */
public class BasicLispInterpreterController implements InterpreterController {
	private InterpreterModel interpreter;
	
//	public BasicLispInterpreterController(ObservableLispInterpreter interpreter) {
//		this.interpreter = interpreter;
//	}
//	
	public void run() {
		if (interpreter == null) {
			throw new IllegalStateException("No interpeter model set when running controller");
		}
		try (Scanner keyboard = new Scanner(System.in)){ 
			String line = keyboard.nextLine();
			while (!line.equals(".")) {
				interpreter.newInput(line);
				
				line = keyboard.nextLine();
			}
		}
	}

	@Override
	public void setModel(InterpreterModel model) {
		interpreter = model;
	}
}
