package main.lisp.interpreter;

import java.beans.PropertyChangeEvent;

import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.Token;

/**
 * <p>The {@code BasicLispInterpreterListener} class implements a basic view
 * for the the lisp interpreter in the MVC framework. The model uses the
 * {@link #propertyChange(PropertyChangeEvent) propertyChangeEvent} callback
 * to notify this class of all tokens from the input and their resulting expressions.
 * 
 * @author Andrew Vitkus
 *
 */
public class BasicLispInterpreterView implements InterpreterView {
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch(evt.getPropertyName()) {
		case ObservableLispInterpreter.TOKEN_PROPERTY:
			Token t = (Token)evt.getNewValue();
			break;
		case ObservableLispInterpreter.EXPRESSION_PROPERTY:
			SExpression raw = (SExpression)evt.getNewValue();
			System.out.println(raw);
			break;
		case ObservableLispInterpreter.RESULT_PROPERTY:
			SExpression evaled = (SExpression)evt.getNewValue();
			System.out.println(evaled);
		}
	}
}
