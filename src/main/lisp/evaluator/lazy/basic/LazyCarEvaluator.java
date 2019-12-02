package main.lisp.evaluator.lazy.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class LazyCarEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'car'");
		}
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'car'");
		}
		
		expr = expr.getHead();
		SExpression evaled = expr.eval(environment);

		if (evaled instanceof NilAtom) {
			return evaled;
		}
		if (evaled instanceof Atom) {
			throw new IllegalStateException("Cannot apply operator 'car' to atomic expressions");
		}
		
		return evaled.getHead();
	}
	
	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'car'");
		}
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'car'");
		}
		
		expr = expr.getHead();
		SExpression evaled = expr.lazyEval(environment);
		
		while (evaled instanceof Thunk) {
			evaled = evaled.lazyEval(environment);
		}

		if (evaled instanceof NilAtom) {
			return evaled;
		}
		if (evaled instanceof Atom) {
			throw new IllegalStateException("Cannot apply operator 'car' to atomic expressions");
		}
		
		return evaled.getHead();
	}
}
