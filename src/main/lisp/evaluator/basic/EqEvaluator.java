package main.lisp.evaluator.basic;

import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;

public class EqEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr) {
		expr = expr.getTail();
		
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			System.err.println("Missing arguments for operator 'eq'");
		}
		SExpression firstExpr = expr.getHead();
		SExpression secondExpr = expr.getTail().getHead();

		SExpression firstEvaled = firstExpr.eval();
		SExpression secondEvaled = secondExpr.eval();
		
		if (!firstEvaled.getClass().equals(secondEvaled.getClass())) {
			return new NilAtom();
		} else {
			if(firstEvaled.equals(secondEvaled)) {
				return new IdentifierAtom("T");
			} else {
				return new NilAtom();
			}
		}
	}

}
