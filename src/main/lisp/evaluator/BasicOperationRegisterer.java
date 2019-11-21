package main.lisp.evaluator;

import main.lisp.evaluator.basic.AtomEvaluator;
import main.lisp.evaluator.basic.CarEvaluator;
import main.lisp.evaluator.basic.CdrEvaluator;
import main.lisp.evaluator.basic.ConsEvaluator;
import main.lisp.evaluator.basic.DifferenceEvaluator;
import main.lisp.evaluator.basic.EqEvaluator;
import main.lisp.evaluator.basic.EqualsEvaluator;
import main.lisp.evaluator.basic.NotEqualsEvaluator;
import main.lisp.evaluator.basic.NullEvaluator;
import main.lisp.evaluator.basic.ProductEvaluator;
import main.lisp.evaluator.basic.QuotientEvaluator;
import main.lisp.evaluator.basic.SumEvaluator;

/**
 * The {@code BasicOperationRegisterer} class provides a means for registering
 * the basic operations required in a minimal lisp interpreter. Only a subset
 * of the necessary lisp operations are included.
 * 
 * @author Andrew Vitkus
 *
 */
public class BasicOperationRegisterer implements OperationRegisterer {
	
	/**
	 * Registers the basic arithmetic operations as <em>+</em>, <em>-</em>, <em>*</em>, and <em>/</em>.
	 * @see SumEvalutor
	 * @see DiferenceEvaluator
	 * @see ProductEvaluator
	 * @see QuotientEvaluator
	 */
	public static void registerArithmetic() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("+", new SumEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("-", new DifferenceEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("*", new ProductEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("/", new QuotientEvaluator());
	}
	
	/**
	 * Registers several basic operations for working with S-Expressions directly.
	 * This set consists of <em>car</car>, <em>cdr</em>, <em>cons</em>, <em>atom</em>, <em>null</em>.
	 * 
	 * @see CarEvaluator
	 * @see CdrEvaluator
	 * @see ConsEvaluator
	 * @see AtomEvaluator
	 * @see NullEvaluator
	 */
	public static void registerExpressionFunctions() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("car", new CarEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("cdr", new CdrEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("cons", new ConsEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("atom", new AtomEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("null", new NullEvaluator());
	}

	/**
	 * Registers a sample comparison expression: <em>eq</eq>, <em>=</em>, <em>/=</em>.
	 * 
	 * @see EqEvaluator
	 * @see EqualsEvaluator
	 * @see NotEqualsEvaluator
	 */
	public static void registerComparison() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("eq", new EqEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("=", new EqualsEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("/=", new NotEqualsEvaluator());
	}
	
	/**
	 * Registers the sample arithmetic, expression, and conditional operations.
	 * 
	 */
	public static void registerAll() {
		registerArithmetic();
		registerExpressionFunctions();
		registerComparison();
	}

	/**
	 * Registers all basic operations.
	 * 
	 * @see #registerAll();
	 */
	@Override
	public void registerOperations() {
		registerAll();
	}
}
