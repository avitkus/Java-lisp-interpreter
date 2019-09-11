package main.lisp.interpreter;

/**
 * This class allows for the class used to implement the interpreter's model
 * per MVC to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class InterpreterModelFactory {
	private static final Class<? extends InterpreterModel> defaultModelClass;
	private static Class<? extends InterpreterModel> modelClass;
	
	static {
		defaultModelClass = ObservableLispInterpreter.class;
		modelClass = defaultModelClass;
	}
	
	/**
	 * This method sets the class to use for implementing a lisp interpreter model per MVC.
	 * 
	 * @param clazz new interpreter model class
	 */
	public static void setClass(Class<? extends InterpreterModel> clazz) {
		modelClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * use as the interpreter model
	 * 
	 * @return the model class
	 */
	public static Class<? extends InterpreterModel> getModelClass() {
		return modelClass;
	}
	
	/**
	 * Construct a new lisp interpreter model.
	 * 
	 * @return the interpreter model
	 */
	public static InterpreterModel newInstance() {
		try {
			return (InterpreterModel) modelClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			try {
				return (InterpreterModel) defaultModelClass.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
