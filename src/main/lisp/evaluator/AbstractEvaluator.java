package main.lisp.evaluator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import main.lisp.parser.terms.SExpression;
import util.models.PropertyListenerRegisterer;

public abstract class AbstractEvaluator implements PropertyListenerRegisterer, Evaluator {
	protected String name;
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public String getName() {
		return name;
	}
	public void setName(String newVal) {
		name = newVal;
	}
	public void evaled(SExpression anInputExpression, SExpression aResultExpression) {
		PropertyChangeEvent anEvent = new PropertyChangeEvent(this, getName(), anInputExpression, aResultExpression);
		propertyChangeSupport.firePropertyChange(anEvent);
	}
	public void removePropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.removePropertyChangeListener(aListener);
	}
	public void clearPropertyChangeListeners() {
		PropertyChangeListener[] aListeners = propertyChangeSupport.getPropertyChangeListeners();
		for (PropertyChangeListener aListener:aListeners) {
			propertyChangeSupport.removePropertyChangeListener(aListener);
		}
	}
	


	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}

}
