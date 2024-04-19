package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;

public class IntegerAtomFactory {
	public static IntegerAtom newInstance(long aLong) {
		return new IntegerAtom(aLong);
	}
	public static IntegerAtom newInstance(Token aToken) {
		return new IntegerAtom(aToken);
	}
}
