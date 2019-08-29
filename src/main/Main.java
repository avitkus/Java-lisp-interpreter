package main;

import java.beans.PropertyChangeListener;

import main.lisp.ObservableLispInterpreter;
import main.lisp.evaluator.BasicOperationRegisterer;

public class Main {
	public static void main(String[] args) {
		BasicOperationRegisterer.registerAll();
		
		ObservableLispInterpreter interpreter = new ObservableLispInterpreter();
		PropertyChangeListener view = new BasicLispInterpreterListener();
		interpreter.registerPropertyChangeListener(view);
		BasicLispInterpreterController controller = new BasicLispInterpreterController(interpreter);
		controller.run();
	}
}
