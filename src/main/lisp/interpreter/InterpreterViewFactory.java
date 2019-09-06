package main.lisp.interpreter;

/**
 * This class allows for the class used to implement the interpreter's view
 * per MVC to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class InterpreterViewFactory {
	private static final Class<? extends InterpreterView> defaultControllerClass;
	private static Class<? extends InterpreterView> controllerClass;
	
	static {
		defaultControllerClass = BasicLispInterpreterView.class;
		controllerClass = defaultControllerClass;
	}
	
	/**
	 * This method sets the class to use for implementing a lisp interpreter
	 * view per MVC.
	 * 
	 * @param clazz new interpreter view class
	 */
	public static void setClass(Class<? extends InterpreterView> clazz) {
		controllerClass = clazz;
	}
	
	/**
	 * Construct a new lisp interpreter view.
	 * 
	 * @return the interpreter view
	 */
	public static InterpreterView newInstance() {
		try {
			return (InterpreterView) controllerClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			try {
				return (InterpreterView) defaultControllerClass.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
