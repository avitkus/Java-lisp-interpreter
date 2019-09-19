package main.lisp.parser.terms;

import main.lisp.evaluator.Environment;
import main.lisp.scanner.tokens.Token;
import util.trace.Tracer;

public abstract class AbstractAtom<T> extends AbstractSExpression implements Atom<T> {
	protected final Token token;
	
	public AbstractAtom(Token token) {
		this.token = token;
	}
	
	@Override
	public SExpression eval(Environment environment) {
		SExpression ret = doEval(environment);
		Tracer.info(this, "Eval: " + this + " --> " + ret);
		return ret;
	}
	
	protected SExpression doEval(Environment environment) {
		return this;
	}
	
	@Override
	public String toString() {
		return token.getValue();
	}
	
	@Override
	public boolean isList() {
		return false;
	}
	
	@Override
	public String toStringAsList() {
		return "";
	}
	
	@Override
	protected String toStringAsListHelper() {
		return toString();
	}
	
	@Override
	public String toStringAsSExpression() {
		return toString();
	}
	
	public String toStringAsSExpressionDeep() {
		return toStringAsSExpression();
	}
}
