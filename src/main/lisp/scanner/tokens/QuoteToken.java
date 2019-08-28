package main.lisp.scanner.tokens;

public class QuoteToken extends AbstractToken {

	public QuoteToken(String value) {
		super("'", TokenType.QUOTE);
	}


	@Override
	public String getValue() {
		return "'";
	}


	@Override
	public String toString() {
		return "quote: '";
	}
}
