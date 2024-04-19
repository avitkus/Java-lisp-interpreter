package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;

public class NilAtomFactory {
	public static NilAtom newInstance() {
		return new NilAtom();
	}	
}
