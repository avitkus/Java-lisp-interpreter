package main.lisp.evaluator.parallel.args;

import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.SExpression;

public interface ArgumentEvaluator {

	public List<SExpression> split(SExpression expr);

	public List<SExpression> evaluate(List<SExpression> expressions, Environment env);
}
