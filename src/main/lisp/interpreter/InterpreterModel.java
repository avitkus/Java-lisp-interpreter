package main.lisp.interpreter;

import java.beans.PropertyChangeListener;

public interface InterpreterModel {
	public void registerPropertyChangeListener(PropertyChangeListener listener);
	public void newInput(String line);
}
