package main.lisp.evaluator.lazy.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.basic.NullEvaluator;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class LazyNullEvaluator extends NullEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();

		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'null'");
		}
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'null'");
		}
		
		SExpression firstEvaled = expr.getHead().lazyEval(environment);
		
		if (firstEvaled instanceof Thunk) {
			firstEvaled = firstEvaled.eval(environment);
		}
		
		if (firstEvaled instanceof NilAtom) {
			return new TAtom();
		} else {
			return new NilAtom();
		}
	}

}
