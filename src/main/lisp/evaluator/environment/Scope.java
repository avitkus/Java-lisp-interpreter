package main.lisp.evaluator.environment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
public interface Scope {

	/**
	 * Looks up the given identifier in the current environment stack
	 * 
	 * @param id the identifier
	 * @return the value if defined or empty
	 */
	public Optional<SExpression> get(IdentifierAtom id);
	
	/**
	 * Looks up the function value for the given identifier in
	 * the current environment stack
	 * 
	 * @param id the identifier
	 * @return the value if defined or empty
	 */
	public Optional<Function> getFun(IdentifierAtom id);
	
	/**
	 * Adds a variable binding in this environment level
	 * 
	 * @param id the identifier
	 * @param value the value
	 */
	public void put(IdentifierAtom id, SExpression value);

	/**
	 * Adds a function binding in this environment level
	 * 
	 * @param id the identifier
	 * @param value the function value
	 */
	public void putFun(IdentifierAtom id, Function value);
	
	public void makeNameSpecial(IdentifierAtom id);
	public void makeLocalSpecial(IdentifierAtom id);
	
	public boolean isSpecial(IdentifierAtom id);
	
	public Map<String, SExpression> getValueMap();
	public Map<String, Function> getFunctionMap();
	public List<String> getSpecialNames();
	public List<String> getLocalSpecialNames();
}
