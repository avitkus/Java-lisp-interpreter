package main.lisp.evaluator;

/**
 * This interface provides a set method for registering operations
 * with the interpreter.
 * 
 * @author Andrew Vitkus
 *
 */
public interface OperationRegisterer {
	/**
	 * Register a set of operations with the operation manager.
	 * 
	 * @see BuiltinOperationManagerSingleton
	 * @see OperationManager#registerEvaluator(String, Evaluator)
	 */
	public void registerOperations();
}
