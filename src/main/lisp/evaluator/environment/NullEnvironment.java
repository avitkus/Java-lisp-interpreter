package main.lisp.evaluator.environment;

import java.util.Optional;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public final class NullEnvironment extends AbstractEnvironment implements Environment {

	@Override
	public Optional<SExpression> lookup(IdentifierAtom id) {
		return Optional.empty();
	}

	@Override
	public Optional<Function> lookupFun(IdentifierAtom id) {
		return Optional.empty();
	}

	@Override
	public void assign(IdentifierAtom id, SExpression value) {
	}

	@Override
	public void assignFun(IdentifierAtom id, Function value) {
	}

	@Override
	public Environment newChild() {
		return this;
	}

	@Override
	public Environment copy() {
		return this;
	}

}
