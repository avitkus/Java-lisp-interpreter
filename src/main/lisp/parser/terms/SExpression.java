package main.lisp.parser.terms;

/**
 * The {@code SExpression} interface provides a model for S-Expressions.
 * 
 * @author Andrew Vitkus
 *
 */
public interface SExpression {
	/**
	 * Evaluates an S-Expression as lisp code.
	 * 
	 * @return S-Expression containing the result of evaluating this expression
	 */
	public SExpression eval();

	/**
	 * Get the head of the S-Expression
	 * 
	 * @return S-Expression's head
	 */
	public SExpression getHead();
	
	/**
	 * Get the tail of the S-Expression
	 * 
	 * @return S-Expression's tail
	 */
	public SExpression getTail();
}
