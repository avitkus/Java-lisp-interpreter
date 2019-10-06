package main;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.OperationRegisterer;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

/**
 * This interface provides a means for getting the classes used to extends
 * the interpreter in case they are needed while grading assignment 2.
 * 
 * @author Andrew Vitkus
 *
 */
public interface ClassRegistryA2 extends ClassRegistry {
	
	/**
	 * Returns a class that can be used to load the variable and lambda
	 * related operation evaluators evaluators.
	 * 
	 * @return operation registerer class
	 */
	public Class<? extends OperationRegisterer> getStatefulOperationRegisterer();
	
	/**
	 * Returns an implementation of an S-Expression that calls lambdas.
	 * 
	 * @return S-Expression class
	 */
	public Class<? extends SExpression> getLambdaCallingSExpression();
	
	/**
	 * Returns an implementation of an identifier atom that looks up variables.
	 * 
	 * @return identifier atom class
	 */
	public Class<? extends IdentifierAtom> getIdentifierAtomWithLookup();
	
	/**
	 * Returns an implementation of an environment that implements lookup and assign
	 * without dynamic scoping.
	 * 
	 * @return environment class
	 */
	public Class<? extends Environment> getNestedLexicalEnvironment();

	/**
	 * Gets the class implementing the "funcall" operation's evaluator.
	 * This version only needs to handle direct lambdas and variables
	 * with lambda value.
	 * 
	 * @return funcall evaluator class
	 */
	public Class<? extends Evaluator> getBasicFuncallEvaluator();

	/**
	 * Gets the class implementing the "lambda" operation's evaluator
	 * 
	 * @return lambda evaluator class
	 */
	public Class<? extends Evaluator> getLambdaEvaluator();

	/**
	 * Gets the class implementing the "setq" operation's evaluator
	 * 
	 * @return setq evaluator class
	 */
	public Class<? extends Evaluator> getSetqEvaluator();
}
