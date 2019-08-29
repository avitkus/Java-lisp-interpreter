package main.lisp.parser.terms;

import java.lang.reflect.InvocationTargetException;

public class ExpressionFactory {
	private static final Class<?> defaultExpressionClass;
	private static Class<?> expressionClass;
	
	static {
		defaultExpressionClass = BasicExpression.class;
		expressionClass = defaultExpressionClass;
	}
	
	public void setClass(Class<? extends SExpression> clazz) {
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
