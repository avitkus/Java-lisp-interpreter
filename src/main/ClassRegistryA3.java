package main;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.OperationRegisterer;
import main.lisp.parser.terms.SExpression;

/**
 * This interface provides a means for getting the classes used to extends
 * the interpreter in case they are needed while grading assignment 2.
 * 
 * @author Andrew Vitkus
 *
 */
public interface ClassRegistryA3 extends ClassRegistryA2 {
	
	/**
	 * Returns a class that can be used to load additional variable and function
	 * related operation evaluators evaluators.
	 * 
	 * @return operation registerer class
	 */
	public Class<? extends OperationRegisterer> getAdditionalStatefulOperationRegisterer();

	/**
	 * Returns an implementation of an S-Expression that calls functions and lambdas.
	 * 
	 * @return S-Expression class
	 */
	public Class<? extends SExpression> getFunctionCallingSExpression();
	
	/**
	 * Returns an implementation of an environment that implements lookup and assign
	 * with lexical and dynamic scoping.
	 * 
	 * @return environment class
	 */
	public Class<? extends Environment> getNestedDynamicEnvironment();

	/**
	 * Gets the class implementing the "defparameter" operation's evaluator
	 * 
	 * @return defparameter evaluator class
	 */
	public Class<? extends Evaluator> getDefparameterEvaluator();

	/**
	 * Gets the class implementing the "defvar" operation's evaluator
	 * 
	 * @return defvar evaluator class
	 */
	public Class<? extends Evaluator> getDefvarEvaluator();

	/**
	 * Gets the class implementing the "let" operation's evaluator
	 * 
	 * @return let evaluator class
	 */
	public Class<? extends Evaluator> getLetEvaluator();

	/**
	 * Gets the class implementing the "function" operation's evaluator
	 * 
	 * @return function evaluator class
	 */
	public Class<? extends Evaluator> getFunctionEvaluator();

	/**
	 * Gets the class implementing the "defun" operation's evaluator
	 * 
	 * @return defun evaluator class
	 */
	public Class<? extends Evaluator> getDefunEvaluator();

	/**
	 * Gets the class implementing the "funcall" operation's complete evaluator.
	 * This version handles lambdas, functions, variables, and function names.
	 * 
	 * @return funcall evaluator class
	 */
	public Class<? extends Evaluator> getCompleteFuncallEvaluator();
}
