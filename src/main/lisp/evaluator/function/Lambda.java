package main.lisp.evaluator.function;

import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.IdentifierAtom;

public interface Lambda extends Atom<String> {
	public IdentifierAtom[] getArgumentNames();
}
