package main.lisp.evaluator.environment;

import main.lisp.evaluator.Environment;

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
public interface ConglomerateEnvironment extends Environment {
	
	public Environment getLexicalEnvironment();
	public Environment getDynamicEnvironment();
}
