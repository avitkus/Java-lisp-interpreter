package main.lisp.evaluator.lazy.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.basic.AtomEvaluator;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class LazyAtomEvaluator extends AtomEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();

		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'atom'");
		}
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'atom'");
		}
		
		SExpression evaled = expr.getHead().lazyEval(environment);
		
		if (evaled instanceof Thunk) {
			evaled = evaled.eval(environment);
		}
		
		if (evaled instanceof Atom) {
			return new TAtom();
		} else {
			return new NilAtom();
		}
	}

}
