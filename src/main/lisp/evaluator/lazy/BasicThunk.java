package main.lisp.evaluator.lazy;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.SExpression;

public class BasicThunk implements Thunk {
	private final SExpression body;
	private final Environment env;
	private SExpression value;
	
	protected BasicThunk(SExpression body, Environment env) {
		this.body = body;
		this.env = env;
		value = null;
	}
	
	@Override
	public SExpression eval(Environment environment) {
		if (value == null) {
			value = body.lazyEval(env);
			while(value instanceof BasicThunk) {
				value = value.lazyEval(env);
			}
		}
		return value;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public String toStringAsList() {
		return toString();
	}

	@Override
	public String toStringAsSExpression() {
		return toString();
	}

	@Override
	public String toStringAsSExpressionDeep() {
		return toString();
	}

	@Override
	public SExpression getValue() {
		return body;
	}
	
	@Override
	public String toString() {
		if (LispInterpreterSettings.doesThunkPrintEval()) {
			return eval(env).toString();
		} else {
			return "Thunk: [" + body + "]";
		}
	}
}
