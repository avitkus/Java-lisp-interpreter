package main.lisp.scanner;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import main.lisp.scanner.tokens.Token;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class BasicScanner implements Scanner {
	private Queue<Token> tokens;
	
	public BasicScanner() {
		tokens = new LinkedList<>();
	}
	
	@Override
	public void setInput(String input) {
		int start = 0;
		int end = 0;
		boolean inToken = false;
		boolean isNumber = false;
		boolean isString = false;
		
		while(end < input.length()) {
			char cur = input.charAt(end);
			
			switch (cur) {
			case ' ':
			case '\t':
			case '(':
			case ')':
				if (inToken && !isString) {
					String tokenVal = input.substring(start, end);
					Token token;
					if (isNumber && !tokenVal.equals("-")) {
						token = numberToToken(tokenVal);
					} else {
						token = textToToken(tokenVal);
					}
					tokens.add(token);
//					start = end + 1;
					isNumber = false;
					inToken = false;
				}
				break;
			}
			
			switch (cur) {
			case ' ':
			case '\t':
				break;
			case '(':
				if (!isString) {
					tokens.add(TokenFactory.newInstance(TokenType.OPEN, "("));
				}
				break;
			case ')':
				if (!isString) {
					tokens.add(TokenFactory.newInstance(TokenType.CLOSE, ")"));
				}
				break;
			case '\'':
				if (!isString) {
					tokens.add(TokenFactory.newInstance(TokenType.QUOTE, "'"));
				}
				break;
			case '-':
				if (inToken) {
					isNumber = false;
					break;
				}
				// explicit fall through if '-' starts token
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				if(!inToken) {
					isNumber = true;
				}
				inToken = true;
				break;
			case '"':
				if (!inToken) {
					isString = true;
					isNumber = false;
					inToken = true;	
				} else if (isString) {
					String tokenVal = input.substring(start, end);
					tokens.add(TokenFactory.newInstance(TokenType.STRING, tokenVal));
					isNumber = false;
					inToken = false;
					isString = false;
				}
				break;
			default:
				isNumber = false;
				inToken = true;	
			}
			
			switch (cur) {
			case ' ':
			case '\t':
			case '(':
			case ')':
			case '\'':
				if (isString) {
					break;
				}
			case '"':
				start = end + 1;
				break;
//			default:
//				end ++;
			}
			end ++;
		}
		if (inToken) {
			String tokenVal = input.substring(start, end);
			Token token;
			if (isString) {
				token = TokenFactory.newInstance(TokenType.STRING, tokenVal);
			} else if (isNumber && !tokenVal.equals("-")) {
				token = numberToToken(tokenVal);
			} else {
				token = textToToken(tokenVal);
			}
			tokens.add(token);
		}
	}

	@Override
	public Optional<Token> nextToken() {
		Token next = tokens.poll();
		if (next == null) {
			return Optional.empty();
		} else {
			return Optional.of(next);
		}
	}
	
	private static Token numberToToken(String tokenVal) {
		if (tokenVal.matches(".*?\\..*+")) {
			return TokenFactory.newInstance(TokenType.DECIMAL, tokenVal);
		} else {
			return TokenFactory.newInstance(TokenType.INTEGER, tokenVal);
		}
	}
	
	private static Token textToToken(String text) {
		return TokenFactory.newInstance(TokenType.IDENTIFIER, text);
	}

}
