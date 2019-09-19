package main.lisp.interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import util.trace.Tracer;

/**
 * This class allows for the class used to implement the interpreter's model
 * per MVC to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class InterpreterModelFactory {
	private static final Class<? extends InterpreterModel> defaultModelClass;
	private static Class<? extends InterpreterModel> modelClass;
	
	static {
		defaultModelClass = ObservableLispInterpreter.class;
		modelClass = defaultModelClass;
	}
	
	/**
	 * This method sets the class to use for implementing a lisp interpreter model per MVC.
	 * 
	 * @param clazz new interpreter model class
	 */
	public static void setClass(Class<? extends InterpreterModel> clazz) {
		try {
			Constructor<? extends InterpreterModel> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(InterpreterModelFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Interpreter model class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Interpreter model class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		modelClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * use as the interpreter model
	 * 
	 * @return the model class
	 */
	public static Class<? extends InterpreterModel> getModelClass() {
		return modelClass;
	}
	
	/**
	 * Construct a new lisp interpreter model.
	 * 
	 * @return the interpreter model
	 */
	public static InterpreterModel newInstance() {
		InterpreterModel ret = null;
		try {
			ret = (InterpreterModel) modelClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			try {
				ret = (InterpreterModel) defaultModelClass.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
		Tracer.info(InterpreterControllerFactory.class, "New interpreter model: " + ret);
		return ret;
	}
}
