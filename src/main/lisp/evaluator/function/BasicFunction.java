package main.lisp.evaluator.function;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.AbstractAtom;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

public class BasicFunction extends AbstractAtom<String> implements Function {
	private final Lambda lambda;
	private final Environment env;

	public BasicFunction(Lambda lambda, Environment env) {
		super(null);
		this.lambda = lambda;
		if (LispInterpreterSettings.isDeepCopyEnvironment()) {
			this.env = env.copy();
		} else {
			this.env = env;
		}
	}

	protected void traceConstruction() {
		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		Tracer.info(this, this.getClass().getSimpleName() + "(" + lambda + ")");
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
	}

	@Override
	public String getValue() {
		return "#<FUNCTION :" + lambda + ">";
	}

	@Override
	public String toStringAsSExpression() {
		return getValue();
	}

	@Override
	public String toString() {
		return getValue();
	}

	@Override
	public SExpression eval(Environment environment) {
		throw new UnsupportedOperationException("This can't be done with this method signature.");
	}

	@Override
	public Lambda getLambda() {
		return lambda;
	}

	@Override
	public Environment getEnvironment() {
		return env;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Function)) {
			return false;
		} else {
			Function fun = (Function) o;
			if (!lambda.equals(fun.getLambda())) {
				return false;
			}
			if (!env.equals(fun.getEnvironment())) {
				return false;
			}
			return true;
		}
	}
}
