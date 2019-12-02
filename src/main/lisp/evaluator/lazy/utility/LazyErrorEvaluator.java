package main.lisp.evaluator.lazy.utility;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.evaluator.utility.ErrorEvaluator;
import main.lisp.parser.terms.SExpression;

public class LazyErrorEvaluator extends ErrorEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		if (expr.isNIL()) {
			throw new IllegalStateException("Missing arguments for operator 'error'");
		}
		
		if (!expr.getTail().isNIL()) {
			throw new IllegalStateException("Too many arguments for operator 'error'");
		}
		
		if (!expr.isAtom()) {
			expr = expr.getHead();
		}

		expr = expr.lazyEval(environment);
		if (expr instanceof Thunk) {
			expr = expr.eval(environment);
		}

		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		System.err.println(expr);
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
		
		throw new IllegalStateException("Error: " + expr);
	}
}
