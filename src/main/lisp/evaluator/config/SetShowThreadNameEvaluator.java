package main.lisp.evaluator.config;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

public class SetShowThreadNameEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if ((expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too few arguments for operator 'setShowThreadName'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'setShowThreadName'");
		}
		SExpression bool = expr.getTail().getHead().eval(environment);
		if (bool instanceof NilAtom) {
			Tracer.setDisplayThreadName(false);
		} else {
			Tracer.setDisplayThreadName(true);
		}
		return bool;
	}

}
