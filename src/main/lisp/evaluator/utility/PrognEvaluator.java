package main.lisp.evaluator.utility;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class PrognEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		SExpression ret = new NilAtom();
		while(!(expr instanceof NilAtom)) {
			ret = expr.getHead().eval(environment);
			expr = expr.getTail();
		}
		
		return ret;
	}
}
