package main.lisp.parser.terms;

import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class NilAtom extends AbstractAtom<String> {

	public NilAtom() {
		super(TokenFactory.newInstance(TokenType.IDENTIFIER, "nil"));
	}

	@Override
	public String getValue() {
		return "nil";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof NilAtom;
	}
	
	@Override
	public String toStringAsList() {
		return "()";
	}
	
	@Override
	public boolean isList() {
		return true;
	}
}
