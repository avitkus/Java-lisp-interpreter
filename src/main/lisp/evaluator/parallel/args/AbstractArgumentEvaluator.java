package main.lisp.evaluator.parallel.args;

import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.SExpression;

public abstract class AbstractArgumentEvaluator implements ArgumentEvaluator {

	public abstract List<SExpression> evaluateParallelWithThreadPool(List<SExpression> expressions, Environment env);

	public abstract List<SExpression> evaluateParallelWithHelperClass(List<SExpression> expressions, Environment env);

	public abstract List<SExpression> evaluateParallelWithLambda(List<SExpression> expressions, Environment env);

	public abstract List<SExpression> evaluateSerial(List<SExpression> expressions, Environment env);

}
