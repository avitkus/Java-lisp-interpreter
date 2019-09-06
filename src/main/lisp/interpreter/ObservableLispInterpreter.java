package main.lisp.interpreter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.lisp.parser.Parser;
import main.lisp.parser.ParserFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.Scanner;
import main.lisp.scanner.ScannerFactory;
import main.lisp.scanner.tokens.Token;

/**
 * The {@code ObservableLispInterpreter} class provides an observable
 * lisp interpreter for use as the model in the MVC pattern. The scanner
 * and parser are created by the {@link ScannerFactory} and {@link ParserFactory}
 * allowing for alternate implementations.
 * 
 * @author Andrew Vitkus
 *
 */
public class ObservableLispInterpreter implements InterpreterModel {
	private final Scanner scanner;
	private final Parser parser;
	private final List<PropertyChangeListener> listeners;
	
	public static final String TOKEN_PROPERTY = "token";
	public static final String EXPRESSION_PROPERTY = "expresssion";
	public static final String RESULT_PROPERTY = "result";
	
	public ObservableLispInterpreter() {
		scanner = ScannerFactory.newInstance();
		parser = ParserFactory.newInstance();
		listeners = new ArrayList<>();
	}
	
	/**
	 * <p> Registers a {@link PropertyChangeListener} instance with the interpreter.
	 * 
	 * <p>The registered listeners will be notified of each {@link #TOKEN_PROPERTY token}
	 * and {@link #EXPRESSION_PROPERTY expression} with each new instance
	 * provided as the {@link java.beans.PropertyChangeEvent#getNewValue() new value}.
	 * 
	 * @param listener listener to receive notifications
	 */
	public void registerPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * <p>Process a {@link String} to tokenize and execute as lisp code.
	 * 
	 * @param line line of lisp code
	 */
	public void newInput(String line) {
		scanner.setInput(line);
		Optional<Token> token = scanner.nextToken();
		while(token.isPresent()) {
			Token realToken = token.get();
			firePropertyChange(TOKEN_PROPERTY, realToken);
			parser.giveToken(realToken);
			token = scanner.nextToken();
		}
		Optional<SExpression> expression = parser.getExpression();
		while(expression.isPresent()) {
			SExpression realExpression = expression.get();
			firePropertyChange(EXPRESSION_PROPERTY, realExpression);
			SExpression result = realExpression.eval(null);
			firePropertyChange(RESULT_PROPERTY, result);
			expression = parser.getExpression();
		}
		token = scanner.nextToken();
	}
	
	private void firePropertyChange(String property, Object value) {
		for(PropertyChangeListener listener : listeners) {
			listener.propertyChange(new PropertyChangeEvent(this, property, null, value));
		}
	}
}
