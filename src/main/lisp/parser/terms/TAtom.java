package main.lisp.parser.terms;

import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class TAtom extends AbstractAtom<String> {

	public TAtom() {
		super(TokenFactory.newInstance(TokenType.IDENTIFIER, "T"));
	}

	@Override
	public String getValue() {
		return "T";
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof TAtom;
	}
}
