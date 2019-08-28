package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;

public abstract class AbstractAtom<T> implements Atom<T> {
	protected final Token token;
	
	public AbstractAtom(Token token) {
		this.token = token;
	}
	
	@Override
	public SExpression eval() {
		return this;
	}
	
	@Override
	public String toString() {
		return token.getValue();
	}

	@Override
	public SExpression getHead() {
		return this;
	}
	
	@Override
	public SExpression getTail() {
		return new NilAtom();
	}
}
