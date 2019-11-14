package main.lisp.evaluator.parallel.args;

import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

public abstract class AbstractArgumentEvaluator implements ArgumentEvaluator {

	private static final String LAMBDA_EVAL_THREAD_NAME_PREFIX = "LambdaEval-";
	private static final String HELPER_CLASS_EVAL_THREAD_NAME_PREFIX = "HelperClassEval-";
	private int lambdaCount = 0;
	private int helperCount = 0;
	
	protected String nextLambdaEvalThreadName() {
		String name = LAMBDA_EVAL_THREAD_NAME_PREFIX + lambdaCount;
		lambdaCount ++;
		Tracer.info(this, "New thread: " + name);
		return name;
	}

	protected String nextHelperClassEvalThreadName() {
		String name = HELPER_CLASS_EVAL_THREAD_NAME_PREFIX + helperCount;
		helperCount ++;
		Tracer.info(this, "New thread: " + name);
		return name;
	}
	
	public abstract List<SExpression> evaluateParallelWithThreadPool(List<SExpression> expressions, Environment env);

	public abstract List<SExpression> evaluateParallelWithHelperClass(List<SExpression> expressions, Environment env);

	public abstract List<SExpression> evaluateParallelWithLambda(List<SExpression> expressions, Environment env);

	public abstract List<SExpression> evaluateSerial(List<SExpression> expressions, Environment env);

}
