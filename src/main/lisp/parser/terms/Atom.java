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
}
