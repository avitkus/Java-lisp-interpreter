package main.lisp.evaluator.basic;

import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;

public class EqEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			System.err.println("Missing arguments for operator 'eq'");
		}
		
		SExpression firstEvaled = expr.getHead().eval();
		SExpression secondEvaled = expr.getTail().eval();
		
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
