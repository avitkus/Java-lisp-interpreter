package main.lisp.interpreter;

public class InterpreterModelSingleton {
	private static InterpreterModel model;
	
	public static void set(InterpreterModel model) {
		InterpreterModelSingleton.model = model;
	}
	
	public static InterpreterModel get() {
		if (model == null) {
			model = InterpreterModelFactory.newInstance();
		}
		return model;
	}
}
