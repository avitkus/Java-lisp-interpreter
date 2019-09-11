package main.lisp.parser;

public class ParserFactory {
	private static final Class<? extends Parser> defaultParserClass;
	private static Class<? extends Parser> parserClass;
	
	static {
		defaultParserClass = BasicParser.class;
		parserClass = defaultParserClass;
	}
	
	public static void setClass(Class<? extends Parser> clazz) {
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
