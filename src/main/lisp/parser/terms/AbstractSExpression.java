package main.lisp.parser.terms;

public abstract class AbstractSExpression implements SExpression {

	protected String toStringAsListHelper() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean isNIL() {
		return false;
	}
	
	@Override
	public boolean isAtom() {
		return false;
	}
}
