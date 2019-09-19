package main.lisp.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class ParserFactory {
	private static final Class<? extends Parser> defaultParserClass;
	private static Class<? extends Parser> parserClass;
	
	static {
		defaultParserClass = BasicParser.class;
		parserClass = defaultParserClass;
	}
	
	public static void setClass(Class<? extends Parser> clazz) {
		try {
			Constructor<? extends Parser> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(ParserFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Parser class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Parser class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		parserClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * use as the parser
	 * 
	 * @return the parser class
	 */
	public static Class<? extends Parser> getParserClass() {
		return parserClass;
	}
	
	public static Parser newInstance() {
		try {
			return (Parser) parserClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			try {
				return (Parser) defaultParserClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
