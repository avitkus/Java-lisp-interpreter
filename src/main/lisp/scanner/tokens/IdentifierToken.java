package main.lisp.scanner.tokens;

public class IdentifierToken extends AbstractToken {
	
	protected IdentifierToken(String value) {
		super(value.toUpperCase(), TokenType.IDENTIFIER);
	}

	@Override
	public String toString() {
		return "text: " + getValue();
	}
}
