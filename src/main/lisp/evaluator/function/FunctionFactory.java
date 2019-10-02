package main.lisp.evaluator.function;

import java.lang.reflect.InvocationTargetException;

import main.lisp.evaluator.Environment;

/**
 * This class allows for the class used to represent lambda expressions in the
 * interpreter to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class FunctionFactory {
	private static final Class<? extends Function> defaultFunctionClass;
	private static Class<? extends Function> functionClass;
	
	static {
		defaultFunctionClass = BasicFunction.class;
		functionClass = defaultFunctionClass;
	}
	
	/**
	 * This method sets the class to use for representing functions.
	 * The class must contain a constructor taking an a {@see Lambda}
	 * as the function's body, and an {@see Environment} as the
	 * function's environment
	 * 
	 * @param clazz new function class
	 * @throws IllegalArgumentException if the specified class does not have a constructor
	 *                                  a {@see Lambda}, and an {@see Environment}
	 */
	public static void setClass(Class<? extends Function> clazz) {
		try {
			clazz.getConstructor(Lambda.class, Environment.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Specified function class does not have requried constructor with arguments (Lambda, Environment)", e);
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		}
		functionClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * representing functions.
	 * 
	 * @return the function class
	 */
	public static Class<? extends Function> getExpressionClass() {
		return functionClass;
	}
	
	/**
	 * Construct a new function with the given body, and environment.
	 * 
	 * @param body function body
	 * @param environment function environment
	 * @return the function
	 */
	public static Function newInstance(Lambda lambda, Environment environment) {
		try {
			return (Function) functionClass.getConstructor(Lambda.class, Environment.class).newInstance(lambda, environment);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			try {
				return (Function) defaultFunctionClass.getConstructor(Lambda.class, Environment.class).newInstance(lambda, environment);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
