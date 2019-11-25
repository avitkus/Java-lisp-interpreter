package main.lisp.evaluator;

import main.lisp.evaluator.config.SetDeepCopyEagerEnvironmentEvaluator;
import main.lisp.evaluator.config.SetDeepCopyFunctionEnvironmentEvaluator;
import main.lisp.evaluator.config.SetDeepCopyLazyEnvironmentEvaluator;
import main.lisp.evaluator.config.SetEagerPoolEvaluator;
import main.lisp.evaluator.config.SetEvalModeEvaluator;
import main.lisp.evaluator.config.SetShowThreadNameEvaluator;
import main.lisp.evaluator.config.SetTracingEvaluator;
import main.lisp.evaluator.parallel.NumThreadsEvaluator;
import main.lisp.evaluator.parallel.PrintThreadEvaluator;
import main.lisp.evaluator.parallel.SleepEvaluator;
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
		registerParallelOperations();
		registerConfigOperations();
		registerTraceOperations();
	}
	
	public static void registerUtilityOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("quit", new QuitEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("print", new PrintEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("error", new ErrorEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("progn", new PrognEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("declare", new DeclareEvaluator());
	}
	
	public static void registerStringOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("char", new CharEvaluator());
//		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("strcat", new StrcatEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("concatenate", new ConcatenateEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("write-to-string", new WriteToStringEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("strlen", new StrlenEvaluator());
	}
	
	public static void registerParallelOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("sleep", new SleepEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("printthread", new PrintThreadEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("numthreads", new NumThreadsEvaluator());
	}
	
	public static void registerConfigOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setDeepCopyEagerEnvironment", new SetDeepCopyEagerEnvironmentEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setDeepCopyLazyEnvironment", new SetDeepCopyLazyEnvironmentEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setDeepCopyFunctionEnvironment", new SetDeepCopyFunctionEnvironmentEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setEvalMode", new SetEvalModeEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setEagerPool", new SetEagerPoolEvaluator());
	}
	
	public static void registerTraceOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setTracing", new SetTracingEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluatorIfNew("setShowThreadName", new SetShowThreadNameEvaluator());
	}
}
