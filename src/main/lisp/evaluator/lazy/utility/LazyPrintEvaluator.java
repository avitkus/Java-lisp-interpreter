package main.lisp.evaluator.lazy.utility;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.evaluator.utility.PrintEvaluator;
import main.lisp.parser.terms.SExpression;

public class LazyPrintEvaluator extends PrintEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		SExpression inputExpr = expr;
		expr = expr.getTail();
		
		if (expr.isNIL()) {
			throw new IllegalStateException("Missing arguments for operator 'print'");
		}
		
		if (!expr.getTail().isNIL()) {
			throw new IllegalStateException("Too many arguments for operator 'print'");
		}
		
		if (!expr.isAtom()) {
			expr = expr.getHead();
		}
		
		expr = expr.lazyEval(environment);
		if (expr instanceof Thunk) {
			expr = expr.eval(environment);
		}
		
		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(true);
		System.out.println(expr);
		evaled(inputExpr, expr);
		
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
		
		return expr;
	}
}
