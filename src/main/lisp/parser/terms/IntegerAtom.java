package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class IntegerAtom extends AbstractAtom<Long> {

	public IntegerAtom(Token token) {
		super(token);
	}

	public IntegerAtom(long l) {
		this(TokenFactory.newInstance(TokenType.INTEGER, Long.toString(l)));
	}

	@Override
	public Long getValue() {
		return Long.parseLong(token.getValue());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IntegerAtom)) {
			return false;
		}
		IntegerAtom i = (IntegerAtom)o;
		return i.getValue().equals(getValue());
	}
}
