package main.lisp.evaluator;

import java.util.Map;

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
	 * Registers the evaluator for a given operation if there is no
	 * existing binding
	 * 
	 * @param name operation name
	 * @param evaluator operation evaluator
	 */
	public void registerEvaluatorIfNew(String name, Evaluator evaluator);
	
	/**
	 * Removes the evaluator for a given operation
	 * 
	 * @param name operation name
	 * @return if an operation was removed
	 */
	public boolean removeEvaluator(String name);
	
	/**
	 * Looks up the evaluator for a given operation
	 * 
	 * @param name operation name
	 * @return the evaluator if one the operation has been registered or 
	 *         {@code null} if not
	 */
	public Evaluator getEvaluator(String name);
	
	public Map<String, Evaluator> getEvaluators();
	
	public void clear();
}
