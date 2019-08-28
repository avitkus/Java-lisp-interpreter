package main.lisp.parser;

public class ParserFactory {
	private static final Class<?> defaultParserClass;
	private static Class<?> parserClass;
	
	static {
		defaultParserClass = BasicParser.class;
		parserClass = defaultParserClass;
	}
	
	public void setClass(Class<? extends Parser> clazz) {
		parserClass = clazz;
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
