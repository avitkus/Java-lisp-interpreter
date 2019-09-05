package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.NilAtom;

public class CdrEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (!(expr.getHead() instanceof Atom) && expr.getTail() instanceof NilAtom) {
			expr = expr.getHead();
		}
		if (expr instanceof NilAtom) {
//			System.err.println("Missing arguments for operator 'car'");
			throw new IllegalStateException("Missing arguments for operator 'car'");
		}
		expr = expr.eval(environment);

		if (expr instanceof Atom && !(expr instanceof NilAtom)) {
//			System.err.println("Cannot apply operator 'cdr' to atomic expressions");
			throw new IllegalStateException("Cannot apply operator 'cdr' to atomic expressions");
		}
		
		if (expr instanceof NilAtom) {
			return expr;
		}
		
		SExpression firstEvaled = expr.getTail();//expr.eval().getTail();
		
		if (firstEvaled instanceof Atom) {
			if (firstEvaled instanceof NilAtom) {
				return firstEvaled;
			} else {
				return ExpressionFactory.newInstance(firstEvaled, new NilAtom());
			}
		} else {
			return firstEvaled;
		}
	}

}
