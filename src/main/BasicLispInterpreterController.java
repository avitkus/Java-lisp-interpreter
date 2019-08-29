package main;

import java.util.Scanner;

import main.lisp.ObservableLispInterpreter;

/**
 * <p>The {@code BasicLispInterpreterController} class creates a controller for
 * the lisp interpreter under the MVC framework. It provides lines to the
 * model ({@link ObservableLispInterpreter}) and handles registering the view
 * ({@link BasicLispInterpreterListener}).
 * 
 * @author Andrew Vitkus
 * 
 * @see BasicLispInterpreterListener
 * @see ObservableLispInterpreter
 *
 */
public class BasicLispInterpreterController {
	private final ObservableLispInterpreter interpreter;
	
	public BasicLispInterpreterController(ObservableLispInterpreter interpreter) {
		this.interpreter = interpreter;
	}
	
	public void run() {
		try (Scanner keyboard = new Scanner(System.in)){ 
			String line = keyboard.nextLine();
			while (!line.equals(".")) {
				interpreter.newInput(line);
				
				line = keyboard.nextLine();
			}
		}
	}
}
