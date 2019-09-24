package main.lisp.evaluator.utility;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class QuitEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		if (expr instanceof NilAtom) {
			System.exit(0);
		}
		
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'quit'");
		}
		
		expr = expr.getHead().eval(environment);
		
		if (!(expr instanceof IntegerAtom)) {
			throw new IllegalStateException("Argument for operator 'quit' must evaluate to an integer");
		}
		
		IntegerAtom retVal = (IntegerAtom)expr;
		
		System.exit(retVal.getValue().intValue());
		
		return null;
	}
}
