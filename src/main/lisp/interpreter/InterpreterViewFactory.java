package main.lisp.interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * This class allows for the class used to implement the interpreter's view
 * per MVC to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class InterpreterViewFactory {
	private static final Class<? extends InterpreterView> defaultViewClass;
	private static Class<? extends InterpreterView> viewClass;
	
	static {
		defaultViewClass = BasicLispInterpreterView.class;
		viewClass = defaultViewClass;
	}
	
	/**
	 * This method sets the class to use for implementing a lisp interpreter
	 * view per MVC.
	 * 
	 * @param clazz new interpreter view class
	 */
	public static void setClass(Class<? extends InterpreterView> clazz) {
		try {
			Constructor<? extends InterpreterView> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(InterpreterViewFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Interpreter view class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Interpreter view class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		viewClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * use as the interpreter view
	 * 
	 * @return the view class
	 */
	public static Class<? extends InterpreterView> getViewClass() {
		return viewClass;
	}
	
	/**
	 * Construct a new lisp interpreter view.
	 * 
	 * @return the interpreter view
	 */
	public static InterpreterView newInstance() {
		try {
			return (InterpreterView) viewClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			try {
				return (InterpreterView) defaultViewClass.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
