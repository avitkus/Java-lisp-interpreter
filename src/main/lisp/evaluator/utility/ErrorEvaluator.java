package main.lisp.evaluator.utility;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class ErrorEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'error'");
		}
		
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'error'");
		}
		
		if (!(expr instanceof Atom)) {
			expr = expr.getHead();
		}

		expr = expr.eval(environment);

		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		System.err.println(expr);
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
		
		throw new IllegalStateException("Error: " + expr);
	}
}
