package main;

import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.OperationRegisterer;
import main.lisp.evaluator.parallel.args.ArgumentEvaluator;
import main.lisp.evaluator.parallel.pool.ThreadPool;
import main.lisp.evaluator.parallel.pool.ThreadPoolWorker;
import main.lisp.parser.terms.SExpression;
import main.util.parallel.Joiner;
import main.util.parallel.MutableJoiner;

/**
 * This interface provides a means for getting the classes used to extends
 * the interpreter in case they are needed while grading assignment 4.
 * 
 * @author Andrew Vitkus
 *
 */
public interface ClassRegistryA4 extends ClassRegistryA3 {

	/**
	 * Returns an implementation of an S-Expression that calls functions and lambdas.
	 * 
	 * @return S-Expression class
	 */
	public Class<? extends SExpression> getFunctionCallingSExpression();

	/**
	 * Gets the class implementing the "let" operation's evaluator
	 * 
	 * @return let evaluator class
	 */
	public Class<? extends Evaluator> getLetEvaluator();

	/**
	 * Gets the class implementing the "defun" operation's evaluator
	 * 
	 * @return defun evaluator class
	 */
	public Class<? extends Evaluator> getDefunEvaluator();

	/**
	 * Gets the class implementing the "defcurry" operation's evaluator
	 * 
	 * @return defcurry evaluator class
	 */
	public Class<? extends Evaluator> getDefcurryEvaluator();

	/**
	 * Gets the class implementing the "funcall" operation's complete evaluator.
	 * This version handles lambdas, functions, variables, and function names.
	 * 
	 * @return funcall evaluator class
	 */
	public Class<? extends Evaluator> getCompleteFuncallEvaluator();
	
	/**
	 * Gets the class implementing an immutable joiner
	 * 
	 * @return immutable joiner class
	 */
	public Class<? extends Joiner> getImmutableJoiner();
	
	/**
	 * Gets the class implementing a mutable joiner
	 * 
	 * @return mutable joiner class
	 */
	public Class<? extends MutableJoiner> getMutableJoiner();
	
	/**
	 * Returns a class that can be used to register eager versions of operators
	 * 
	 * @return eager operation registerer class
	 */
	public Class<? extends OperationRegisterer> getEagerOperationRegisterer();
	
	public Class<? extends ThreadPool> getThreadPool();
	
	public Class<? extends ThreadPoolWorker> getThreadPoolWorker();
	
	public Class<? extends ArgumentEvaluator> getArgumentEvaluator();
	
	public Class<? extends SExpression> getEagerSExpression();
	public Class<? extends Evaluator> getEagerCurry();
	public Class<? extends Evaluator> getEagerDefcurry();
	public Class<? extends Evaluator> getEagerFuncall();
	public Class<? extends Evaluator> getEagerAnd();
	public Class<? extends Evaluator> getEagerOr();
	public Class<? extends Evaluator> getEagerList();
}
