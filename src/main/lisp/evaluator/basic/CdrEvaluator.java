package main.lisp.evaluator.basic;

import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.BasicExpression;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.NilAtom;

public class CdrEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr) {
		expr = expr.getTail();
		expr = expr.eval();
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom) {
			System.err.println("Missing arguments for operator 'cdr'");
		}

		if (expr instanceof Atom) {
			System.err.println("Cannot apply operator 'cdr' to atomic expressions");
		}
		
		
		SExpression firstEvaled = expr.getTail();//expr.eval().getTail();
		
		if (firstEvaled instanceof Atom) {
			return new BasicExpression(firstEvaled, new NilAtom());
		} else {
			return firstEvaled;
		}
	}

}
