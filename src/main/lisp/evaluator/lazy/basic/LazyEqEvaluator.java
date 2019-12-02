package main.lisp.evaluator.lazy.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.basic.EqEvaluator;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class LazyEqEvaluator extends EqEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'eq'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'eq'");
		}
		SExpression firstExpr = expr.getHead();
		SExpression secondExpr = expr.getTail().getHead();

		SExpression firstEvaled = firstExpr.lazyEval(environment);
		SExpression secondEvaled = secondExpr.lazyEval(environment);
		
		if (firstEvaled instanceof Thunk) {
			firstEvaled = firstEvaled.eval(environment);
		}
		if (secondEvaled instanceof Thunk) {
			secondEvaled = secondEvaled.eval(environment);
		}
		
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
