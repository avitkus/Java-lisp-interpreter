package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;

public class StringAtomFactory {
	
	public static StringAtom newInstance(Token aToken) {
		return new StringAtom(aToken);
	}
}
