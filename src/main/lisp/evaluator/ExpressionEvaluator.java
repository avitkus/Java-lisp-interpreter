package main.lisp.evaluator;

import main.lisp.parser.terms.SExpression;

public interface ExpressionEvaluator {
	public SExpression eval(SExpression expr, Environment env);
	public SExpression lazyEval(SExpression expr, Environment env);
}
