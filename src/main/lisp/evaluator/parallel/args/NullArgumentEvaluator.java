package main.lisp.evaluator.parallel.args;

import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.SExpression;

public final class NullArgumentEvaluator implements ArgumentEvaluator {

	@Override
	public List<SExpression> split(SExpression expr) {
		throw new UnsupportedOperationException("To use this you must register a new class with the ArgumentEvaluatorSingleton");
	}

	@Override
	public List<SExpression> evaluate(List<SExpression> expressions, Environment env) {
		throw new UnsupportedOperationException("To use this you must register a new class with the ArgumentEvaluatorSingleton");
	}
}
