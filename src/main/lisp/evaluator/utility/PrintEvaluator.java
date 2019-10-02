package main.lisp.evaluator.utility;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class PrintEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
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

		System.out.println(expr);
		
		return expr;
	}
}
