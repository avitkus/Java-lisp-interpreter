package main.lisp.evaluator.parallel;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class SleepEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if (expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing argument for operator 'sleep'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'eq'");
		}
		SExpression timeExpr = expr.getTail().getHead().eval(environment);
		if (!(timeExpr instanceof IntegerAtom)) {
			throw new IllegalStateException("Argument for operator 'sleep' must evaluate a number");
		}
		IntegerAtom time = (IntegerAtom)timeExpr;
		try {
			Thread.sleep(time.getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return time;
	}

}
