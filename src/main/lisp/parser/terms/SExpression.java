package main.lisp.parser.terms;

import main.lisp.evaluator.Environment;

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
	 * @param environment TODO
	 * @return S-Expression containing the result of evaluating this expression
	 */
	public SExpression eval(Environment environment);

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
	
	/**
	 * Determines if this S-Expression represents a list.
	 * 
	 * @return {@code true} if a list, otherwise {@code false}
	 */
	public boolean isList();
	
	/**
	 * Creates a {@code String} representation of list contained in
	 * this S-Expression or an empty string if this is not a list.
	 * 
	 * @return formatted list
	 */
	public String toStringAsList();
	
	/**
	 * Creates a {@code String} representation of this S-Expression
	 * 
	 * @return formatted S-Expression
	 */
	public String toStringAsSExpression();
	
	public String toStringAsSExpressionDeep();
}
