package main.lisp.scanner.tokens;

public class StringToken extends AbstractToken {
	
	public StringToken(String value) {
		super(value, TokenType.STRING);
	}

	@Override
	public String toString() {
		return "string: " + getValue();
	}
}
