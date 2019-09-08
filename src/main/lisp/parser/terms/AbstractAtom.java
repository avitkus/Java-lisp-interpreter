package main.lisp.parser.terms;

import main.lisp.evaluator.Environment;
import main.lisp.scanner.tokens.Token;

public abstract class AbstractAtom<T> implements Atom<T> {
	protected final Token token;
	
	public AbstractAtom(Token token) {
		this.token = token;
	}
	
	@Override
	public SExpression eval(Environment environment) {
		return this;
	}
	
	@Override
	public String toString() {
		return toStringAsSExpression();
	}

	@Override
	public SExpression getHead() {
		return this;
	}
	
	@Override
	public SExpression getTail() {
		return new NilAtom();
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
	public String toStringAsSExpression() {
		return token.getValue();
	}
	
	public String toStringAsSExpressionDeep() {
		return toStringAsSExpression();
	}
}
