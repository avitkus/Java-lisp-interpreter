package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;

public class NullEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();

		if (expr instanceof NilAtom) {
//			System.err.println("Missing arguments for operator 'null'");
			throw new IllegalStateException("Missing arguments for operator 'null'");
		}
		
		SExpression firstEvaled = expr.eval(environment);
		
		if (firstEvaled instanceof NilAtom) {
			return new IdentifierAtom("T");
		} else {
			return new NilAtom();
		}
	}

}
