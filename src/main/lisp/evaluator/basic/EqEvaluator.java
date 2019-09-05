package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class EqEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
//			System.err.println("Missing arguments for operator 'eq'");
			throw new IllegalStateException("Missing arguments for operator 'eq'");
		}
		SExpression firstExpr = expr.getHead();
		SExpression secondExpr = expr.getTail().getHead();

		SExpression firstEvaled = firstExpr.eval(environment);
		SExpression secondEvaled = secondExpr.eval(environment);
		
		if (!firstEvaled.getClass().equals(secondEvaled.getClass())) {
			return new NilAtom();
		} else {
			if(firstEvaled.equals(secondEvaled)) {
				return new TAtom();
			} else {
				return new NilAtom();
			}
		}
	}

}
