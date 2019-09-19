package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class CdrEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'cdr'");
		}
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'cdr'");
		}
		
		expr = expr.getHead();
			
		expr = expr.eval(environment);

		if (expr instanceof NilAtom) {
			return expr;
		}
		
		if (expr instanceof Atom) {
			throw new IllegalStateException("Cannot apply operator 'cdr' to atomic expressions");
		}
		
		return expr.getTail();
	}

}
