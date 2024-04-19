package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;

public class DecimalAtomFactory {
	public static DecimalAtom newInstance(long aLong) {
		return new DecimalAtom(aLong);
	}
	public static DecimalAtom newInstance(Token aToken) {
		return new DecimalAtom(aToken);
	}

}
