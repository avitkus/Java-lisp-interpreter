package main;

import main.lisp.evaluator.BasicOperationRegisterer;
import main.lisp.interpreter.InterpreterController;
import main.lisp.interpreter.InterpreterControllerFactory;
import main.lisp.interpreter.InterpreterModel;
import main.lisp.interpreter.InterpreterModelFactory;
import main.lisp.interpreter.InterpreterView;
import main.lisp.interpreter.InterpreterViewFactory;

public class Main {
	public static void main(String[] args) {
		BasicOperationRegisterer.registerAll();
		
		InterpreterModel interpreter = InterpreterModelFactory.newInstance();
		InterpreterView view = InterpreterViewFactory.newInstance();
		InterpreterController controller = InterpreterControllerFactory.newInstance();
		
		interpreter.registerPropertyChangeListener(view);
		controller.setModel(interpreter);
		
		controller.run();
	}
}
