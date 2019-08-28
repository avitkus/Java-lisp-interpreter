package main.lisp.scanner;

import java.util.Optional;

import main.lisp.scanner.tokens.Token;

/**
 * The {@code Scanner} interface provides a standardized method for
 * converting {@link String Strings} to {@link Token Tokens}. Tokens
 * are provided one at a time, even if the given {@code Strings}
 * contain multiple.
 * 
 * @author Andrew Vitkus
 * @see Token
 *
 */
public interface Scanner {
	
	/**
	 * Provides a {@link String} to the scanner for tokenizing.
	 * 
	 * @param input line of lisp code
	 */
	public void setInput(String input);
	
	/**
	 * 
	 * @return the next {@link Token} encoded by the current input
	 *         or empty if no tokens are available 
	 */
	public Optional<Token> nextToken();
}
