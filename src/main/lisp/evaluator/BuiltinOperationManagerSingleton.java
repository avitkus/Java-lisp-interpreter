package main.lisp.evaluator;

public class BuiltinOperationManagerSingleton {
	private static final Class<?> defaultOperationManagerClass;
	private static Class<?> operationManagerClass;
	
	private static OperationManager builtinOperationManager;
	
	static {
		defaultOperationManagerClass = BasicOperationManager.class;
		operationManagerClass = defaultOperationManagerClass;
	}
	
	public void setClass(Class<? extends OperationManager> clazz) {
		operationManagerClass = clazz;
	}
	
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
