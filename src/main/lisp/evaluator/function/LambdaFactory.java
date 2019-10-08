package main.lisp.evaluator.function;

import java.lang.reflect.InvocationTargetException;

import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

/**
 * This class allows for the class used to represent lambda expressions in the
 * interpreter to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class LambdaFactory {
	private static final Class<? extends Lambda> defaultLambdaClass;
	private static Class<? extends Lambda> lambdaClass;
	
	static {
		defaultLambdaClass = BasicLambda.class;
		lambdaClass = defaultLambdaClass;
	}
	
	/**
	 * This method sets the class to use for representing lambda expressions.
	 * The class must contain a constructor taking an array of {@see IdentifierAtom IdentifierAtoms}
	 * for argument names and an {@see SExpression} as the body
	 * 
	 * @param clazz new lambda class
	 * @throws IllegalArgumentException if the specified class does not have a constructor
	 *                                  taking array of {@see IdentifierAtom IdentifierAtoms}
	 *                                  and an {@see SExpression}
	 */
	public static void setClass(Class<? extends Lambda> clazz) {
		try {
			clazz.getConstructor(IdentifierAtom[].class, SExpression.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Specified lambda class does not have requried constructor with arguments (IdentifierAtom[], SExpression)", e);
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		}
		lambdaClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * representing lambda expressions.
	 * 
	 * @return the lambda expression class
	 */
	public static Class<? extends Lambda> getExpressionClass() {
		return lambdaClass;
	}
	
	/**
	 * Construct a new lambda expression with the given argument names and body.
	 * 
	 * @param argNames argument names in order
	 * @param body lambda body
	 * @return the lambda expression
	 */
	public static Lambda newInstance(IdentifierAtom[] argNames, SExpression body) {
		Lambda ret = null;
		try {
			ret = (Lambda) lambdaClass.getConstructor(IdentifierAtom[].class, SExpression.class).newInstance(argNames, body);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			try {
				ret = (Lambda) defaultLambdaClass.getConstructor(IdentifierAtom[].class, SExpression.class).newInstance(argNames, body);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
		Tracer.info(LambdaFactory.class, "New lambda: " + ret);
		return ret;
	}
}
