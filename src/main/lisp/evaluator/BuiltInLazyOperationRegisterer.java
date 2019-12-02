package main.lisp.evaluator;

import main.lisp.evaluator.lazy.basic.LazyAtomEvaluator;
import main.lisp.evaluator.lazy.basic.LazyCarEvaluator;
import main.lisp.evaluator.lazy.basic.LazyCdrEvaluator;
import main.lisp.evaluator.lazy.basic.LazyConsEvaluator;
import main.lisp.evaluator.lazy.basic.LazyDifferenceEvaluator;
import main.lisp.evaluator.lazy.basic.LazyEqEvaluator;
import main.lisp.evaluator.lazy.basic.LazyEqualsEvaluator;
import main.lisp.evaluator.lazy.basic.LazyNotEqualsEvaluator;
import main.lisp.evaluator.lazy.basic.LazyNullEvaluator;
import main.lisp.evaluator.lazy.basic.LazyProductEvaluator;
import main.lisp.evaluator.lazy.basic.LazyQuotientEvaluator;
import main.lisp.evaluator.lazy.basic.LazySumEvaluator;
import main.lisp.evaluator.lazy.string.LazyCharEvaluator;
import main.lisp.evaluator.lazy.string.LazyConcatenateEvaluator;
import main.lisp.evaluator.lazy.string.LazyStrlenEvaluator;
import main.lisp.evaluator.lazy.string.LazyWriteToStringEvaluator;
import main.lisp.evaluator.lazy.utility.LazyErrorEvaluator;
import main.lisp.evaluator.lazy.utility.LazyPrintEvaluator;
import main.lisp.evaluator.lazy.utility.LazyPrognEvaluator;
import main.lisp.evaluator.lazy.utility.LazyQuitEvaluator;

public class BuiltInLazyOperationRegisterer implements OperationRegisterer {

	@Override
	public void registerOperations() {
		registerAll();
	}
	
	public static void registerAll() {
		registerArithmetic();
		registerComparison();
		registerExpressionFunctions();
		registerUtilityOperations();
		registerStringOperations();
	}
	
	public static void registerArithmetic() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("+", new LazySumEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("-", new LazyDifferenceEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("*", new LazyProductEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("/", new LazyQuotientEvaluator());
	}
	
	public static void registerComparison() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("eq", new LazyEqEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("=", new LazyEqualsEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("/=", new LazyNotEqualsEvaluator());
	}
	
	public static void registerExpressionFunctions() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("car", new LazyCarEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("cdr", new LazyCdrEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("cons", new LazyConsEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("atom", new LazyAtomEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("null", new LazyNullEvaluator());
	}
	
	public static void registerUtilityOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("quit", new LazyQuitEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("print", new LazyPrintEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("error", new LazyErrorEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("progn", new LazyPrognEvaluator());
	}
	
	public static void registerStringOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("char", new LazyCharEvaluator());
//		BuiltinOperationManagerSingleton.get().registerEvaluator("strcat", new LazyStrcatEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("concatenate", new LazyConcatenateEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("write-to-string", new LazyWriteToStringEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("strlen", new LazyStrlenEvaluator());
	}
}
