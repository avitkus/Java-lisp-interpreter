package main.lisp.parser;

import java.util.List;
import java.util.Optional;

import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.Token;

/**
 * <p>The {@code Parser} interface provides a standardized method for
 * mapping {@link Token Tokens} to {@link SExpression Expressions}.
 * Expressions are returned one at a time, even if multiple can be
 * created with the current tokens.
 * 
 * @author Andrew Vitkus
 * @see Token
 * @see SExpression
 *
 */
public interface Parser {
	/**
	 * Processes a list of tokens in order.
	 * 
	 * @param tokens list of tokens
	 */
	public void setTokens(List<Token> tokens);
	
	/**
	 * Processes a single token.
	 * 
	 * @param token one token
	 */
	public void giveToken(Token token);
	
	/**
	 * 
	 * @return the {@link SExpression S-Expression} if a full expression was available or
	 * 		   empty if not
	 */
	public Optional<SExpression> getExpression();
}
