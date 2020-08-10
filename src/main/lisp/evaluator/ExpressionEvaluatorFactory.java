package main.lisp.evaluator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import util.trace.Tracer;

/**
 * This class allows for the class used to evaluate S-expressions
 * 
 * @author Andrew Vitkus
 *
 */
public class ExpressionEvaluatorFactory {
	private static final Class<? extends ExpressionEvaluator> defaultExpressionEvalClass;
	private static Class<? extends ExpressionEvaluator> expressionEvalClass;
	
	static {
		defaultExpressionEvalClass = BasicExpressionEvaluator.class;
		expressionEvalClass = defaultExpressionEvalClass;
	}
	
	/**
	 * This method sets the class to use for evaluating S-expressions
	 * 
	 * @param clazz new S-expression evaluator class
	 */
	public static void setClass(Class<? extends ExpressionEvaluator> clazz) {
		try {
			Constructor<? extends ExpressionEvaluator> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(ExpressionEvaluatorFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("S-expression evaluator class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("S-expression evaluator class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		expressionEvalClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * use as the S-expression evaluator
	 * 
	 * @return the S-Expression evaluator class
	 */
	public static Class<? extends ExpressionEvaluator> getViewClass() {
		return expressionEvalClass;
	}
	
	/**
	 * Construct a new S-expression evaluator.
	 * 
	 * @return the S-expression evaluator
	 */
	public static ExpressionEvaluator newInstance() {
		ExpressionEvaluator ret = null;
		try {
			ret = (ExpressionEvaluator) expressionEvalClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			try {
				ret = (ExpressionEvaluator) defaultExpressionEvalClass.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
		Tracer.info(ExpressionEvaluatorFactory.class, "New S-expression evaluator: " + ret);
		return ret;
	}
}
