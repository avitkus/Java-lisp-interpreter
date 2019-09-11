package main.lisp.scanner.tokens;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class allows the classes used to represent the default tokens to be
 * replaced and new tokens added.
 * 
 * @author Andrew Vitkus
 *
 */
public class TokenFactory {
	private static Map<String, Class<? extends Token>> defaultTokenTypeMap;
	private static Map<String, Class<? extends Token>> tokenTypeMap;
	
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
	
	/**
	 * Sets the class for a default token type.
	 * 
	 * @param type token type
	 * @param clazz token class
	 * @throws IllegalArgumentException if registering a token for the {@link TokenType#OTHER} type
	 * @throws IllegalArgumentException if the token class does not have a construction taking
	 *                                  a {@link String} argument
	 */
	public static void setTokenClass(TokenType type, Class<? extends Token> clazz) {
		if (type == TokenType.OTHER) {
			throw new IllegalArgumentException("Non-default tokens must be registered with their type, not other");
		}
		setTokenClass(type.name(), clazz);
	}
	
	/**
	 * Sets the class for any token type.
	 * 
	 * @param type token type name
	 * @param clazz token class
	 * @throws IllegalArgumentException if the token class does not have a construction taking
	 *                                  a {@link String} argument
	 */
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
	
	/**
	 * This method returns the class currently registered for use as
	 * a default token type.
	 * 
	 * @param tokenType the token type
	 * @return the token class
	 * @throws IllegalArgumentException if looking up a token for the {@link TokenType#OTHER} type
	 */
	public static Class<? extends Token> getTokenClass(TokenType tokenType) {
		if (tokenType == TokenType.OTHER) {
			throw new IllegalArgumentException("Non-default tokens are registered with their type, not other");
		}
		return tokenTypeMap.get(tokenType.name());
	}

	
	/**
	 * This method returns the class currently registered for use as
	 * a default token type.
	 * 
	 * @param tokenType the token type
	 * @return the token class or null if not none registered
	 */
	public static Class<? extends Token> getTokenClass(String tokenType) {
		return tokenTypeMap.get(tokenType);
	}
	
	/**
	 * Creates a new token with the given type and value.
	 * 
	 * @param type token type
	 * @param value token value
	 * @return token
	 * @throws IllegalArgumentException if getting a token for the {@link TokenType#OTHER} type
	 */
	public static Token newInstance(TokenType type, String value) {
		if (type == TokenType.OTHER) {
			throw new IllegalArgumentException("Other is not a valid token type");
		}
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
	
	/**
	 * Creates a new token with the given type name and value.
	 * 
	 * @param type token type name
	 * @param value token value
	 * @return token
	 * @throws IllegalArgumentException if no class is registered for this token type
	 */
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
