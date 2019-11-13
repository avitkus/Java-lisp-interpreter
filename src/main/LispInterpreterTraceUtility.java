package main;

import main.lisp.evaluator.BasicOperationManager;
import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import main.lisp.evaluator.environment.AbstractEnvironment;
import main.lisp.evaluator.environment.BasicConglomerateEnvironment;
import main.lisp.evaluator.environment.BasicScope;
import main.lisp.evaluator.environment.EnvironmentFactory;
import main.lisp.evaluator.function.FunctionFactory;
import main.lisp.evaluator.function.LambdaFactory;
import main.lisp.evaluator.parallel.args.ArgumentEvaluatorSingleton;
import main.lisp.evaluator.parallel.pool.AbstractThreadPool;
import main.lisp.evaluator.parallel.pool.ThreadPoolSingleton;
import main.lisp.interpreter.InterpreterControllerFactory;
import main.lisp.interpreter.InterpreterModelFactory;
import main.lisp.interpreter.InterpreterViewFactory;
import main.lisp.parser.ParserFactory;
import main.lisp.parser.terms.AbstractSExpression;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.scanner.ScannerFactory;
import main.lisp.scanner.tokens.TokenFactory;
import util.trace.Tracer;

public class LispInterpreterTraceUtility {
	/**
	 * Do not change this, I will keep updating it and you will run into conflicts
	 * Make a copy if you want this changed
	 */
	public static void setTracing() {
		Tracer.showInfo(true);
		
		// Operation
		Tracer.setKeywordPrintStatus(BasicOperationManager.class, true);
		Tracer.setKeywordPrintStatus(AbstractSExpression.class, true);
		Tracer.setKeywordPrintStatus(AbstractEnvironment.class, true);
		Tracer.setKeywordPrintStatus(BasicScope.class, true);
		Tracer.setKeywordPrintStatus(BasicConglomerateEnvironment.class, true);
		Tracer.setKeywordDisplayStatus(LispInterpreterSettings.class, true);
		Tracer.setKeywordDisplayStatus(AbstractThreadPool.class, true);
		
		// Factories
		Tracer.setKeywordPrintStatus(TokenFactory.class, true);
		Tracer.setKeywordPrintStatus(ExpressionFactory.class, true);
		Tracer.setKeywordPrintStatus(ScannerFactory.class, true);
		Tracer.setKeywordPrintStatus(ParserFactory.class, true);
		Tracer.setKeywordPrintStatus(InterpreterModelFactory.class, true);
		Tracer.setKeywordPrintStatus(InterpreterViewFactory.class, true);
		Tracer.setKeywordPrintStatus(InterpreterControllerFactory.class, true);
		Tracer.setKeywordPrintStatus(BuiltinOperationManagerSingleton.class, true);
		Tracer.setKeywordPrintStatus(EnvironmentFactory.class, true);
		Tracer.setKeywordPrintStatus(LambdaFactory.class, true);
		Tracer.setKeywordPrintStatus(FunctionFactory.class, true);
		Tracer.setKeywordPrintStatus(ThreadPoolSingleton.class, true);
		Tracer.setKeywordPrintStatus(ArgumentEvaluatorSingleton.class, true);
	}
}