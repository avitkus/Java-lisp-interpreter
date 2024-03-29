package main.lisp.evaluator.environment;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import main.lisp.evaluator.Environment;
import util.trace.Tracer;

/**
 * This class allows the class used to represent the execution environment
 * replaced.
 * 
 * @author Andrew Vitkus
 *
 */
public class EnvironmentFactory {
	private static final Class<? extends Environment> defaultEnvironmentClass;
	private static Class<? extends Environment> environmentClass;
	
	static {
		defaultEnvironmentClass = NullEnvironment.class;
		environmentClass = defaultEnvironmentClass;
	}

	/**
	 * Sets the class for managing the execution environment.
	 * 
	 * @param clazz environment class
	 * @throws IllegalArgumentException if the environment class does not have a constructor taking
	 *                                  an {@link Environment} argument
	 * @throws IllegalArgumentException if the environment class' constructor is not accessible
	 */
	public static void setClass(Class<? extends Environment> clazz) {
		try {
			Constructor<? extends Environment> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(EnvironmentFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Environment class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Environment class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		Tracer.info(EnvironmentFactory.class, "Setting environment class to:" + clazz);
		environmentClass = clazz;
	}

	/**
	 * Creates a new environment with no parent.
	 * 
	 * @return the environment
	 */
	public static Environment newInstance() {
		Environment env = null;
		try {
			env = (Environment) environmentClass.newInstance();
			Tracer.info(EnvironmentFactory.class, "Created environment:" + env);

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			try {
				env = (Environment) defaultEnvironmentClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		Tracer.info(EnvironmentFactory.class, "New root environment:\n" + env);
		return env;
	}
}
