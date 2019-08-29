package main.lisp.scanner.tokens;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TokenFactory {
	private static Map<String, Class<?>> defaultTokenTypeMap;
	private static Map<String, Class<?>> tokenTypeMap;
	
	static {
		defaultTokenTypeMap = new HashMap<>();
		tokenTypeMap = new HashMap<>();
		
		defaultTokenTypeMap.put(TokenType.OPEN.name(), OpenToken.class);
		tokenTypeMap.put(TokenType.OPEN.name(), OpenToken.class);
		
		defaultTokenTypeMap.put(TokenType.CLOSE.name(), CloseToken.class);
		tokenTypeMap.put(TokenType.CLOSE.name(), CloseToken.class);
		
		defaultTokenTypeMap.put(TokenType.DECIMAL.name(), DecimalToken.class);
		tokenTypeMap.put(TokenType.DECIMAL.name(), DecimalToken.class);
		
		defaultTokenTypeMap.put(TokenType.INTEGER.name(), IntegerToken.class);
		tokenTypeMap.put(TokenType.INTEGER.name(), IntegerToken.class);
		
		defaultTokenTypeMap.put(TokenType.IDENTIFIER.name(), IdentifierToken.class);
		tokenTypeMap.put(TokenType.IDENTIFIER.name(), IdentifierToken.class);
		
		defaultTokenTypeMap.put(TokenType.STRING.name(), StringToken.class);
		tokenTypeMap.put(TokenType.STRING.name(), StringToken.class);
		
		defaultTokenTypeMap.put(TokenType.QUOTE.name(), QuoteToken.class);
		tokenTypeMap.put(TokenType.QUOTE.name(), QuoteToken.class);
	}
	
	public static void setTokenClass(TokenType type, Class<? extends Token> clazz) {
		setTokenClass(type.name(), clazz);
	}
	
	public static void setTokenClass(String type, Class<? extends Token> clazz) {
		try {
			clazz.getConstructor(String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Specified '" + type + "' token does not have requried constructor with arguments (String)", e);
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		}
		tokenTypeMap.put(type, clazz);
	}
	
	public static Token newInstance(TokenType type, String value) {
		try {
			return (Token) tokenTypeMap.get(type.name()).getConstructor(String.class).newInstance(value);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			try {
				return (Token) defaultTokenTypeMap.get(type.name()).getConstructor(String.class).newInstance(value);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
	
	public static Token newInstance(String type, String value) {
		try {
			if (!tokenTypeMap.containsKey(type)) {
				throw new IllegalArgumentException("No class registered for token '" + type + "'");
			}
			return (Token) tokenTypeMap.get(type).getConstructor(String.class).newInstance(value);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			try {
				if (defaultTokenTypeMap.containsKey(value)) {
					return (Token) defaultTokenTypeMap.get(type).getConstructor(String.class).newInstance(value);
				} else {
					return null;
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
