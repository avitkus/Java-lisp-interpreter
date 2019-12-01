package main.lisp.evaluator.utility;

import main.LispInterpreterSettings;
import main.lisp.evaluator.AbstractEvaluator;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class PrintEvaluator extends AbstractEvaluator implements Evaluator {
	public PrintEvaluator() {
		setName("print");
	}
	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		SExpression inputExpr = expr;
		expr = expr.getTail();
		
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'print'");
		}
		
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'print'");
		}
		
		if (!(expr instanceof Atom)) {
			expr = expr.getHead();
		}
		
		expr = expr.eval(environment);
		
		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(true);
		System.out.println(expr);
		evaled(inputExpr, expr);

		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
		
		return expr;
	}
}
