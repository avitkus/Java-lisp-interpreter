package main.lisp.evaluator.config;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class SetDeepCopyLazyEnvironmentEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if ((expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too few arguments for operator 'setDeepCopyLazyEnvironment'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'setDeepCopyLazyEnvironment'");
		}
		SExpression bool = expr.getTail().getHead().eval(environment);
		if (bool instanceof NilAtom) {
			LispInterpreterSettings.setDeepCopyLazyEnvironment(false);
		} else {
			LispInterpreterSettings.setDeepCopyLazyEnvironment(true);
		}
		return bool;
	}

}
