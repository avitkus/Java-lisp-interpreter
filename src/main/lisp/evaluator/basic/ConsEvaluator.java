package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.NilAtom;

public class ConsEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			System.err.println("Missing arguments for operator 'cons'");
		}
		
		SExpression firstEvaled = expr.getHead().eval();
		SExpression secondEvaled = expr.getTail().eval();
		
		SExpression ret = ExpressionFactory.newInstance(firstEvaled, secondEvaled);
		return ret;
	}

}
