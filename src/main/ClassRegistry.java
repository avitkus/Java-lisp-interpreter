package main;

import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.OperationRegisterer;
import main.lisp.parser.terms.SExpression;

/**
 * This interface provides a means for getting the classes used to extends
 * the interpreter in case they are needed while grading.
 * 
 * @author Andrew Vitkus
 *
 */
public interface ClassRegistry {
	/**
	 * Returns the class containing the main method for grading.
	 * 
	 * @return main class
	 */
	public Class<?> getMain();
	
	/**
	 * Returns a class that can be used to load any custom operation evaluators.
	 * 
	 * @return operation registerer class
	 */
	public Class<? extends OperationRegisterer> getCustomOperationRegisterer();
	
	/**
	 * Returns an implementation of an S-Expression with an implemented toString method.
	 * 
	 * @return S-Expression class
	 */
	public Class<? extends SExpression> getStringFormattingSExpression();
	
	/**
	 * Gets the class implementing the "quote" operation's evaluator
	 * 
	 * @return quote evaluator class
	 */
	public Class<? extends Evaluator> getQuoteEvaluator();
	
	/**
	 * Gets the class implementing the "eval" operation's evaluator
	 * 
	 * @return eval evaluator class
	 */
	public Class<? extends Evaluator> getEvalEvaluator();
	
	/**
	 * Gets the class implementing the "load" operation's evaluator
	 * 
	 * @return quote evaluator class
	 */
	public Class<? extends Evaluator> getLoadEvaluator();

	/**
	 * Gets the class implementing the "cond" operation's evaluator
	 * 
	 * @return cond evaluator class
	 */
	public Class<? extends Evaluator> getCondEvaluator();

	/**
	 * Gets the class implementing the "list" operation's evaluator
	 * 
	 * @return list evaluator class
	 */
	public Class<? extends Evaluator> getListEvaluator();
	

	/**
	 * Gets the class implementing the "<" operation's evaluator
	 * 
	 * @return < evaluator class
	 */
	public Class<? extends Evaluator> getLTEvaluator();

	/**
	 * Gets the class implementing the ">" operation's evaluator
	 * 
	 * @return > evaluator class
	 */
	public Class<? extends Evaluator> getGTEvaluator();

	/**
	 * Gets the class implementing the "<=" operation's evaluator
	 * 
	 * @return <= evaluator class
	 */
	public Class<? extends Evaluator> getLTEEvaluator();

	/**
	 * Gets the class implementing the ">=" operation's evaluator
	 * 
	 * @return >= evaluator class
	 */
	public Class<? extends Evaluator> getGTEEvaluator();
	

	/**
	 * Gets the class implementing the "and" operation's evaluator
	 * 
	 * @return and evaluator class
	 */
	public Class<? extends Evaluator> getAndEvaluator();
	
	/**
	 * Gets the class implementing the "or" operation's evaluator
	 * 
	 * @return or evaluator class
	 */
	public Class<? extends Evaluator> getOrEvaluator();
	
	/**
	 * Gets the class implementing the "not" operation's evaluator
	 * 
	 * @return not evaluator class
	 */
	public Class<? extends Evaluator> getNotEvaluator();
}
