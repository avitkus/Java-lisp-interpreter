package main.lisp.evaluator;

import main.lisp.evaluator.lazy.Thunk;
import main.lisp.parser.terms.SExpression;

/**
 * The {@code Evaluator} interface provide a means for executing
 * the lisp code contained in S-Expressions.
 * 
 * @author Andrew Vitkus
 *
 */
public interface Evaluator {
	
	/**
	 * Evaluates the lisp code represented by an {@link SExpression S-Expression}.
	 * 
	 * @param expr S-Expression to evaluate
	 * @param environment TODO
	 * @return result of evaluation
	 * @throws IllegalStateException if this expression is malformed
	 */
	public SExpression eval(SExpression expr, Environment environment);
	
	public default SExpression lazyEval(SExpression expr, Environment environment) {
		return eval(expr, environment);
	}
}
