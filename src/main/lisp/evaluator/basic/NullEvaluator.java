package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class NullEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();

		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'null'");
		}
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'null'");
		}
		
		SExpression firstEvaled = expr.getHead().eval(environment);
		
		if (firstEvaled instanceof NilAtom) {
			return new TAtom();
		} else {
			return new NilAtom();
		}
	}

}
