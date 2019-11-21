package main.lisp.evaluator.lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

/**
 * This class allows for the class used to represent thunks in the
 * interpreter to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class ThunkFactory {
	private static final Class<? extends Thunk> defaultThunkClass;
	private static Class<? extends Thunk> thunkClass;
	
	static {
		defaultThunkClass = BasicThunk.class;
		thunkClass = defaultThunkClass;
	}
	
	/**
	 * This method sets the class to use for representing thunks.
	 * The class must contain a constructor taking an a {@see SExpression}
	 * as the thunk's body, and an {@see Environment} as the
	 * thunk's environment
	 * 
	 * @param clazz ThunkFactory.newInstance class
	 * @throws IllegalArgumentException if the specified class does not have a constructor
	 *                                  a {@see SExpression}, and an {@see Environment}
	 */
	public static void setClass(Class<? extends Thunk> clazz) {
		try {
			Constructor<? extends Thunk> c = clazz.getDeclaredConstructor(SExpression.class, Environment.class);
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(ExpressionFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Thunk class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Specified thunk class does not have requried constructor with arguments (SExpression, Environment)", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		thunkClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * representing thunks.
	 * 
	 * @return the thunk class
	 */
	public static Class<? extends Thunk> getExpressionClass() {
		return thunkClass;
	}
	
	/**
	 * Construct a ThunkFactory.newInstance with the given body, and environment.
	 * 
	 * @param body thunk body
	 * @param environment thunk environment
	 * @return the thunk
	 */
	public static Thunk newInstance(SExpression body, Environment environment) {
		Thunk ret = null;
		try {
			ret = (Thunk) thunkClass.getDeclaredConstructor(SExpression.class, Environment.class).newInstance(body, environment);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			try {
				ret = (Thunk) defaultThunkClass.getDeclaredConstructor(SExpression.class, Environment.class).newInstance(body, environment);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
//		Tracer.info(FunctionFactory.class, "New function: " + ret);
		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		Tracer.info(ThunkFactory.class, "ThunkFactory.newInstance: " + "\n\tbody:" +body + "\n\tenvironment:" + environment);
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);

		return ret;
	}
}
