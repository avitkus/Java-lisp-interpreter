package main.lisp.evaluator.lazy;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.SExpression;

public class Thunk implements Atom<SExpression> {
	private final SExpression body;
	private final Environment env;
	
	public Thunk(SExpression body, Environment env) {
		this.body = body;
		this.env = env;
	}
	
	@Override
	public SExpression eval(Environment environment) {
		SExpression result = body.lazyEval(env);
		while(result instanceof Thunk) {
			result = result.lazyEval(env);
		}
		return result;
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
		return "Thunk: [" + body + "]";
	}
}
