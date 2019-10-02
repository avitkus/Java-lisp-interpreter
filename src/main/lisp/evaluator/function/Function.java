package main.lisp.evaluator.function;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.Atom;

public interface Function extends Atom<String> {
	public Lambda getLambda();
	public Environment getEnvironment();
}
