package main;

import main.lisp.evaluator.BasicOperationRegisterer;
import main.lisp.interpreter.InterpreterController;
import main.lisp.interpreter.InterpreterControllerSingleton;
import main.lisp.interpreter.InterpreterModel;
import main.lisp.interpreter.InterpreterModelSingleton;
import main.lisp.interpreter.InterpreterView;
import main.lisp.interpreter.InterpreterViewSingleton;

public class Main {
	public static void main(String[] args) {
		BasicOperationRegisterer.registerAll();
		
		InterpreterModel interpreter = InterpreterModelSingleton.get();
		InterpreterView view = InterpreterViewSingleton.get();
		InterpreterController controller = InterpreterControllerSingleton.get();
		
		interpreter.registerPropertyChangeListener(view);
		controller.setModel(interpreter);
		
		controller.run();
	}
}
