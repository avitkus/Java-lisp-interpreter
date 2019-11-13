package main.lisp.evaluator.config;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;

public class SetEvalModeEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if ((expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too few arguments for operator 'setEvalMode'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'setEvalMode'");
		}
		SExpression mode = expr.getTail().getHead().eval(environment);
		if (!(mode instanceof StringAtom)) {
			throw new IllegalStateException("Argument for operator 'setEvalMode' must be string with valid evaluation mode");
		}
		StringAtom modeStrAtom = (StringAtom)mode;
		String modeStr = modeStrAtom.getValue().toUpperCase();
		switch(modeStr) {
			case "EAGER":
				LispInterpreterSettings.setEvaluationMode(LispInterpreterSettings.EvaluationMode.EAGER);
			break;
			case "LAZY":
				LispInterpreterSettings.setEvaluationMode(LispInterpreterSettings.EvaluationMode.LAZY);
			break;
			case "NORMAL":
				LispInterpreterSettings.setEvaluationMode(LispInterpreterSettings.EvaluationMode.NORMAL);
			break;
			default:
				throw new IllegalStateException("Too many arguments for operator 'setEvalMode'");
		}
		return mode;
	}

}
