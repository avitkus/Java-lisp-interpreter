package main.lisp.evaluator.lazy.utility;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.evaluator.utility.QuitEvaluator;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class LazyQuitEvaluator extends QuitEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		if (expr instanceof NilAtom) {
			System.exit(0);
		}
		
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'quit'");
		}
		
		expr = expr.getHead().lazyEval(environment);
		if (expr instanceof Thunk) {
			expr = expr.eval(environment);
		}
		
		if (!(expr instanceof IntegerAtom)) {
			throw new IllegalStateException("Argument for operator 'quit' must evaluate to an integer");
		}
		
		IntegerAtom retVal = (IntegerAtom)expr;
		
		System.exit(retVal.getValue().intValue());
		
		return null;
	}
}
