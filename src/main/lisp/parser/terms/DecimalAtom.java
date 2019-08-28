package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class DecimalAtom extends AbstractAtom<Double> {

	public DecimalAtom(Token token) {
		super(token);
	}

	public DecimalAtom(double d) {
		this(TokenFactory.newInstance(TokenType.DECIMAL, Double.toString(d)));
	}

	@Override
	public Double getValue() {
		return Double.parseDouble(token.getValue());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DecimalAtom)) {
			return false;
		}
		DecimalAtom d = (DecimalAtom)o;
		return d.getValue().equals(getValue());
	}
}
