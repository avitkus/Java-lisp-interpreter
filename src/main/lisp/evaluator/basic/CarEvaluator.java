package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.NilAtom;

public class CarEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
//			System.err.println("Missing arguments for operator 'car'");
			throw new IllegalStateException("Missing arguments for operator 'car'");
		}
		
		if (expr instanceof Atom && !(expr instanceof NilAtom)) {
//			System.err.println("Cannot apply operator 'car' to atomic expressions");
			throw new IllegalStateException("Cannot apply operator 'car' to atomic expressions");
		}
		
		SExpression firstEvaled = expr.eval(environment).getHead();
		
		return firstEvaled;
	}

}
