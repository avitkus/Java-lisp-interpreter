package main.lisp.evaluator.lazy.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.basic.ConsEvaluator;
import main.lisp.evaluator.lazy.ThunkFactory;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class LazyConsEvaluator extends ConsEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		SExpression inputExpr = expr;
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'cons'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'cons'");
		}
		
		SExpression firstEvaled = ThunkFactory.newInstance(expr.getHead(), environment);
		SExpression secondEvaled = ThunkFactory.newInstance(expr.getTail().getHead(), environment);
		
		SExpression ret = ExpressionFactory.newInstance(firstEvaled, secondEvaled);

		evaled(inputExpr, ret);
		
		return ret;
	}
}
