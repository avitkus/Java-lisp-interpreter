package main.lisp.interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

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
	public static void setClass(Class<? extends InterpreterController> clazz) {
		try {
			Constructor<? extends InterpreterController> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(InterpreterControllerFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Interpreter controller class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Interpreter controller class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		controllerClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * use as the interpreter controller
	 * 
	 * @return the controller class
	 */
	public static Class<? extends InterpreterController> getControllerClass() {
		return controllerClass;
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
