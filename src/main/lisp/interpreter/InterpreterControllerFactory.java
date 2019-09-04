package main.lisp.interpreter;

/**
 * This class allows for the class used to implement the interpreter's controller
 * per MVC to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class InterpreterControllerFactory {
	private static final Class<? extends InterpreterController> defaultControllerClass;
	private static Class<? extends InterpreterController> controllerClass;
	
	static {
		defaultControllerClass = BasicLispInterpreterController.class;
		controllerClass = defaultControllerClass;
	}
	
	/**
	 * This method sets the class to use for implementing a lisp interpreter
	 * controller per MVC.
	 * 
	 * @param clazz new interpreter controller class
	 */
	public void setClass(Class<? extends InterpreterController> clazz) {
		controllerClass = clazz;
	}
	
	/**
	 * Construct a new lisp interpreter controller.
	 * 
	 * @return the interpreter controller
	 */
	public static InterpreterController newInstance() {
		try {
			return (InterpreterController) controllerClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			try {
				return (InterpreterController) defaultControllerClass.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
