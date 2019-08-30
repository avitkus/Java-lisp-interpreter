package main.lisp.evaluator;

/**
 * The {@code OperationManager} interface provides a set method for
 * registering and looking up the {@link Evaluator} instances for
 * operations in the interpreter.
 * 
 * @author Andrew Vitkus
 *
 */
public interface OperationManager {
	/**
	 * Registers the evaluator for a given operation
	 * 
	 * @param name operation name
	 * @param evaluator operation evaluator
	 */
	public void registerEvaluator(String name, Evaluator evaluator);
	
	/**
	 * Looks up the evaluator for a given operation
	 * 
	 * @param name operation name
	 * @return the evaluator if one the operation has been registered or 
	 *         {@code null} if not
	 */
	public Evaluator getEvaluator(String name);
}
