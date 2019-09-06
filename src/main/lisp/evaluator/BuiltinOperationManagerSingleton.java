package main.lisp.evaluator;

/**
 * The {@code BuiltinOperationManagerSingleton} class provides access
 * to a single point accessing an {@link OperationMangager} for
 * registering and accessing operations with the interpreter.
 * 
 * @author Andrew Vitkus
 *
 * @see OperationManager
 */
public class BuiltinOperationManagerSingleton {
	private static final Class<? extends OperationManager> defaultOperationManagerClass;
	private static Class<? extends OperationManager> operationManagerClass;
	
	private static OperationManager builtinOperationManager;
	
	static {
		defaultOperationManagerClass = BasicOperationManager.class;
		operationManagerClass = defaultOperationManagerClass;
	}
	
	/**
	 * Sets the class for use as the built-in operation manager. 
	 * 
	 * @param clazz built-in operation manager class
	 * @throws UnsupportedOperationException if changing the class after the singleton has
	 *         been created
	 */
	public static void setClass(Class<? extends OperationManager> clazz) {
		if (builtinOperationManager != null) {
			throw new UnsupportedOperationException("Cannot change built-in operation manager class after instantiation");
		}
		operationManagerClass = clazz;
	}
	
	/**
	 * Gets the {@link OperationManager} for built-in operations, creating it
	 * with specified class on first call.
	 * 
	 * @return built-in operation manager
	 */
	public static OperationManager get() {
		if (builtinOperationManager == null) {
			createInstance();
		}
		return builtinOperationManager;
	}
	
	private static void createInstance() {
		try {
			builtinOperationManager = (OperationManager) operationManagerClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			try {
				builtinOperationManager = (OperationManager) defaultOperationManagerClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
				builtinOperationManager = null;
			}
		}
	}
}
