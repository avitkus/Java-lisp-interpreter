package main.lisp.evaluator;

import main.lisp.evaluator.config.SetDeepCopyEagerEnvironmentEvaluator;
import main.lisp.evaluator.config.SetDeepCopyFunctionEnvironmentEvaluator;
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
	
	public static void registerParallelOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("sleep", new SleepEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("printthread", new PrintThreadEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("numthreads", new NumThreadsEvaluator());
	}
	
	public static void registerConfigOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("setDeepCopyEagerEnvironment", new SetDeepCopyEagerEnvironmentEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("setDeepCopyFunctionEnvironment", new SetDeepCopyFunctionEnvironmentEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("setEvalMode", new SetEvalModeEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("setEagerPool", new SetEagerPoolEvaluator());
	}
	
	public static void registerTraceOperations() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("setTracing", new SetTracingEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("setShowThreadName", new SetShowThreadNameEvaluator());
	}
}
