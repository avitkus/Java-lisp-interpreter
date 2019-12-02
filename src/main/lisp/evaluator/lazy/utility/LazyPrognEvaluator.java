package main.lisp.evaluator.lazy.utility;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.evaluator.utility.PrognEvaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class LazyPrognEvaluator extends PrognEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		SExpression ret = new NilAtom();
		while(!(expr instanceof NilAtom)) {
			ret = expr.getHead().lazyEval(environment);
			expr = expr.getTail();
		}
		if (ret instanceof Thunk) {
			ret = ret.eval(environment);
		}
		return ret;
	}
}
