package main.lisp.evaluator;

import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public class BasicExpressionEvaluator implements ExpressionEvaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if (expr.getHead() instanceof IdentifierAtom) {
			String operator = ((IdentifierAtom)expr.getHead()).getValue();
			Evaluator eval = BuiltinOperationManagerSingleton.get().getEvaluator(operator);
			if (eval == null) {
				throw new IllegalStateException("No evaluator registered for operator '" + operator + "'");
			}
			return eval.eval(expr, environment);
		} else {
			throw new IllegalStateException("Expression does not start with an operator");
		}
	}

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		if (expr.getHead() instanceof IdentifierAtom) {
			String operator = ((IdentifierAtom)expr.getHead()).getValue();
			Evaluator eval = BuiltinOperationManagerSingleton.get().getEvaluator(operator);
			if (eval == null) {
				throw new IllegalStateException("No evaluator registered for operator '" + operator + "'");
			}
			return eval.lazyEval(expr, environment);
		} else {
			throw new IllegalStateException("Expression does not start with an operator");
		}
	}
}
