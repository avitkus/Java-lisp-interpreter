package main;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.lisp.ObservableLispInterpreter;
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
public class BasicLispInterpreterListener implements PropertyChangeListener {
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch(evt.getPropertyName()) {
		case ObservableLispInterpreter.TOKEN_PROPERTY:
			Token t = (Token)evt.getNewValue();
			break;
		case ObservableLispInterpreter.EXPRESSION_PROPERTY:
			SExpression e = (SExpression)evt.getNewValue();
//			System.out.println(e);
			SExpression evaled = e.eval(null);
			System.out.println(evaled);
		}
	}
}
