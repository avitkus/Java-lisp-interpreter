package main.lisp.parser.terms;

/**
 * The {@code Atom} interface provides a model for lisp atoms.
 * 
 * @author Andrew Vitkus
 *
 * @param <T> Java type used to represent the type an atom stores
 */
public interface Atom<T> extends SExpression {
	
	/**
	 * Get the value of this atom
	 * 
	 * @return atom's value
	 */
	public T getValue();
	

	/**
	 * An atom does not have a head. This could be implemented to return itself and still
	 * function, but this helps avoid using them incorrectly.
	 * 
	 */
	@Override
	default public SExpression getHead() {
		throw new UnsupportedOperationException("Instances of Atom do not have a head");
	}

	/**
	 * An atom does not have a tail. This could be implemented to return some known value
	 * (i.e {@code null}) and still function, but this is more clear.
	 */
	@Override
	default public SExpression getTail() {
		throw new UnsupportedOperationException("Instances of Atom do not have a tail");
	}
}
