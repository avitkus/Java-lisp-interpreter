package main.lisp.interpreter;

public class InterpreterViewSingleton {
	private static InterpreterView view;
	
	public static void set(InterpreterView view) {
		InterpreterViewSingleton.view = view;
	}
	
	public static InterpreterView get() {
		if (view == null) {
			view = InterpreterViewFactory.newInstance();
		}
		return view;
	}
}
