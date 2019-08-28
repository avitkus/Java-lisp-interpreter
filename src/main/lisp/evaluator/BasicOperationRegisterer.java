package main.lisp.evaluator;

import main.lisp.evaluator.basic.AtomEvaluator;
import main.lisp.evaluator.basic.CarEvaluator;
import main.lisp.evaluator.basic.CdrEvaluator;
import main.lisp.evaluator.basic.ConsEvaluator;
import main.lisp.evaluator.basic.DifferenceEvaluator;
import main.lisp.evaluator.basic.EqEvaluator;
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
public class BasicOperationRegisterer {
	
	/**
	 * Registers the basic arithmetic operations as <em>+</em>, <em>-</em>, <em>*</em>, and <em>/</em>.
	 * @see SumEvalutor
	 * @see DiferenceEvaluator
	 * @see ProductEvaluator
	 * @see QuotientEvaluator
	 */
	public static void registerArithmetic() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("+", new SumEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("-", new DifferenceEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("*", new ProductEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("/", new QuotientEvaluator());
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
		BuiltinOperationManagerSingleton.get().registerEvaluator("car", new CarEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("cdr", new CdrEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("cons", new ConsEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("atom", new AtomEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("null", new NullEvaluator());
	}

	/**
	 * Registers a sample conditional expression: <em>eq</eq>.
	 * 
	 * @see EqEvaluator
	 */
	public static void registerConditional() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("eq", new EqEvaluator());
	}
	
	/**
	 * Registers the sample arithmetic, expression, and conditional operations.
	 * 
	 */
	public static void registerAll() {
		registerArithmetic();
		registerExpressionFunctions();
		registerConditional();
	}
}
