package main.lisp.interpreter;

public class InterpreterControllerSingleton {
	private static InterpreterController controller;
	
	public static void set(InterpreterController controller) {
		InterpreterControllerSingleton.controller = controller;
	}
	
	public static InterpreterController get() {
		if (controller == null) {
			controller = InterpreterControllerFactory.newInstance();
		}
		return controller;
	}
}
