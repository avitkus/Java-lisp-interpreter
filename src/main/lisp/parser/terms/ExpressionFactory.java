package main.lisp.parser.terms;

import java.lang.reflect.InvocationTargetException;

/**
 * This class allows for the class used to represent S-Expressions in the interpreter
 * to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class ExpressionFactory {
	private static final Class<? extends SExpression> defaultExpressionClass;
	private static Class<? extends SExpression> expressionClass;
	
	static {
		defaultExpressionClass = BasicExpression.class;
		expressionClass = defaultExpressionClass;
	}
	
	/**
	 * This method sets the class to use for representing non-atomic S-Expressions.
	 * The class must contain a constructor taking two instances of {@link SExpression}.
	 * 
	 * @param clazz new s-expression class
	 * @throws IllegalArgumentException if the specified class does not have a constructor
	 *                                  taking two instances of {@link SExpression}
	 */
	public static void setClass(Class<? extends SExpression> clazz) {
		try {
			clazz.getConstructor(SExpression.class, SExpression.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Specified S-Expression class does not have requried constructor with arguments (SExpression, SExpression)", e);
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		}
		expressionClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * representing non-atomic s-expressions.
	 * 
	 * @return the s-expression class
	 */
	public static Class<? extends SExpression> getExpressionClass() {
		return expressionClass;
	}
	
	/**
	 * Construct a new S-Expression with the given head and tail.
	 * 
	 * @param head head S-Expression
	 * @param tail tail S-Expression
	 * @return the combined S-Expression
	 */
	public static SExpression newInstance(SExpression head, SExpression tail) {
		try {
			return (SExpression) expressionClass.getConstructor(SExpression.class, SExpression.class).newInstance(head, tail);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			try {
				return (SExpression) defaultExpressionClass.getConstructor(SExpression.class, SExpression.class).newInstance(head, tail);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
