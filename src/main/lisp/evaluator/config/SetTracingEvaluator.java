package main.lisp.evaluator.config;

import main.LispInterpreterTraceUtility;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

public class SetTracingEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if ((expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too few arguments for operator 'setTracing'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'setTracing'");
		}
		SExpression bool = expr.getTail().getHead().eval(environment);
		if (bool instanceof NilAtom) {
			Tracer.showInfo(false);
		} else {
			LispInterpreterTraceUtility.setTracing();
		}
		return bool;
	}

}
