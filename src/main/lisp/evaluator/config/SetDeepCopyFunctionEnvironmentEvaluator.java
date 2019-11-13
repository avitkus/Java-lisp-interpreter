package main.lisp.evaluator.config;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class SetDeepCopyFunctionEnvironmentEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if ((expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too few arguments for operator 'setDeepCopyFunctionEnvironment'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'setDeepCopyFunctionEnvironment'");
		}
		SExpression bool = expr.getTail().getHead().eval(environment);
		if (bool instanceof NilAtom) {
			LispInterpreterSettings.setDeepCopyFunctionEnvironment(false);
		} else {
			LispInterpreterSettings.setDeepCopyFunctionEnvironment(true);
		}
		return bool;
	}

}
