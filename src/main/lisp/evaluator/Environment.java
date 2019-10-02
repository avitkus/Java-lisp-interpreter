package main.lisp.evaluator;

import java.util.Optional;

import main.lisp.evaluator.environment.Scope;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

/**
 * The {@code Environment} interface provides a common definition
 * for the execution environment in the interpreter.
 * 
 * </br></br> <em>Note: this is still not finalized. As of this version
 * only variables have been flushed out so functions may require
 * more changes.</em>
 * 
 * @author Andrew Vitkus
 *
 */
public interface Environment extends Scope{
	
	/**
	 * Looks up the given identifier in the current environment stack
	 * 
	 * @param id the identifier
	 * @return the value if defined or empty
	 */
	public Optional<SExpression> lookup(IdentifierAtom id);
	
	/**
	 * Looks up the function value for the given identifier in
	 * the current environment stack
	 * 
	 * @param id the identifier
	 * @return the value if defined or empty
	 */
	public Optional<Function> lookupFun(IdentifierAtom id);

	/**
	 * Reassigns a variable
	 * 
	 * @param id the identifier
	 * @param value the value
	 */
	public void assign(IdentifierAtom id, SExpression value);

	/**
	 * Assigns a global function definition
	 * 
	 * @param id the identifier
	 * @param value the function value
	 */
	public void assignFun(IdentifierAtom id, Function value);
	
	/**
	 * Creates a new environment level over the current
	 * 
	 * @return the new environment
	 */
	public Environment newChild();
	
	public Environment getParent();
	
	public Environment copy();
}
