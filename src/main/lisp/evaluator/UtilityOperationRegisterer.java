package main.lisp.evaluator;

import main.lisp.evaluator.string.CharEvaluator;
import main.lisp.evaluator.string.ConcatenateEvaluator;
import main.lisp.evaluator.string.StrlenEvaluator;
import main.lisp.evaluator.string.WriteToStringEvaluator;
import main.lisp.evaluator.utility.DeclareEvaluator;
import main.lisp.evaluator.utility.ErrorEvaluator;
import main.lisp.evaluator.utility.PrintEvaluator;
import main.lisp.evaluator.utility.PrognEvaluator;
import main.lisp.evaluator.utility.QuitEvaluator;

public class UtilityOperationRegisterer implements OperationRegisterer {

	@Override
	public void registerOperations() {
		registerAll();
	}
	
	public static void registerAll() {
		registerUtilityOperations();
		registerStringOperations();
	}
	
	public static void registerUtilityOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("quit", new QuitEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("print", new PrintEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("error", new ErrorEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("progn", new PrognEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("declare", new DeclareEvaluator());
	}
	
	public static void registerStringOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("char", new CharEvaluator());
//		BuiltinOperationManagerSingleton.get().registerEvaluator("strcat", new StrcatEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("concatenate", new ConcatenateEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("write-to-string", new WriteToStringEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("strlen", new StrlenEvaluator());
	}
}
