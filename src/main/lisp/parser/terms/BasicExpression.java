package main.lisp.parser.terms;

import main.LispInterpreterSettings;
import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import util.trace.Tracer;

public class BasicExpression extends AbstractSExpression {
	private final SExpression head;
	private final SExpression tail;
	
	protected BasicExpression(SExpression head, SExpression tail) {
		this.head = head;
		this.tail = tail;

		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		Tracer.info(this, this.getClass().getSimpleName() + "(" + head + ", " + tail + ")");
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
	}
	
	@Override
	public SExpression eval(Environment environment) {
		if (head instanceof IdentifierAtom) {
			String operator = ((IdentifierAtom)head).getValue();
			Evaluator eval = BuiltinOperationManagerSingleton.get().getEvaluator(operator);
			if (eval == null) {
				throw new IllegalStateException("No evaluator registered for operator '" + operator + "'");
			}
			return eval.eval(this, environment);
		} else {
			throw new IllegalStateException("Expression does not start with an operator");
		}
	}

	@Override
	public SExpression lazyEval(Environment environment) {
		if (head instanceof IdentifierAtom) {
			String operator = ((IdentifierAtom)head).getValue();
			Evaluator eval = BuiltinOperationManagerSingleton.get().getEvaluator(operator);
			if (eval == null) {
				throw new IllegalStateException("No evaluator registered for operator '" + operator + "'");
			}
			return eval.lazyEval(this, environment);
		} else {
			throw new IllegalStateException("Expression does not start with an operator");
		}
	}
	
	@Override
	public SExpression getHead() {
		return head;
	}

	@Override
	public SExpression getTail() {
		return tail;
	}

	@Override
	public boolean isList() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public String toStringAsList() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public String toStringAsSExpression() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public String toStringAsSExpressionDeep() {
		throw new UnsupportedOperationException("Not implemented");
	}
}
