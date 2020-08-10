package main.lisp.parser.terms;

import main.LispInterpreterSettings;
import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.ExpressionEvaluatorSingleton;
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
		return ExpressionEvaluatorSingleton.get().eval(this, environment);
	}

	@Override
	public SExpression lazyEval(Environment environment) {
		return ExpressionEvaluatorSingleton.get().lazyEval(this, environment);
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
