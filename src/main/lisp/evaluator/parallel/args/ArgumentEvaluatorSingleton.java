package main.lisp.evaluator.parallel.args;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import util.trace.Tracer;

/**
 * The {@code ArgumentEvaluatorSingleton} class provides access
 * to a single point accessing an {@link ArgumentEvaluator} for
 * evaluating argument lists
 * 
 * @author Andrew Vitkus
 *
 * @see ArgumentEvaluator
 */
public class ArgumentEvaluatorSingleton {
	private static final Class<? extends ArgumentEvaluator> defaultArgumentEvaluatorClass;
	private static Class<? extends ArgumentEvaluator> argumentEvaluatorClass;
	
	private static ArgumentEvaluator argumentEvaluator;
	
	static {
		defaultArgumentEvaluatorClass = NullArgumentEvaluator.class;
		argumentEvaluatorClass = defaultArgumentEvaluatorClass;
	}
	
	/**
	 * Sets the class for use as the argument evaluator. 
	 * 
	 * @param clazz argument evaluator class
	 * @throws UnsupportedOperationException if changing the class after the singleton has
	 *         been created
	 */
	public static void setClass(Class<? extends ArgumentEvaluator> clazz) {
		if (argumentEvaluator != null) {
//			throw new UnsupportedOperationException("Cannot change argument evaluator class after instantiation");
			System.err.println("Cannot change argument evaluator class after instantiation");
		}
		try {
			Constructor<? extends ArgumentEvaluator> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(ArgumentEvaluatorSingleton.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Argument evaluator class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Argument evaluator class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		argumentEvaluatorClass = clazz;
	}
	
	/**
	 * Gets the {@link ArgumentEvaluator} for built-in operations, creating it
	 * with specified class on first call.
	 * 
	 * @return argument evaluator
	 */
	public static ArgumentEvaluator get() {
		if (argumentEvaluator == null) {
			createInstance();
		}
		return argumentEvaluator;
	}
	
	private static void createInstance() {
		try {
			argumentEvaluator = (ArgumentEvaluator) argumentEvaluatorClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			try {
				argumentEvaluator = (ArgumentEvaluator) defaultArgumentEvaluatorClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
				argumentEvaluator = null;
			}
		}
		Tracer.info(ArgumentEvaluatorSingleton.class, "New argument evaluator: " + argumentEvaluator);
	}
}
