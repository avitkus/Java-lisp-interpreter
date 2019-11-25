package main.lisp.evaluator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import main.lisp.parser.terms.SExpression;
import util.models.PropertyListenerRegisterer;

public abstract class AbstractEvaluator implements PropertyListenerRegisterer {
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


	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}

}
