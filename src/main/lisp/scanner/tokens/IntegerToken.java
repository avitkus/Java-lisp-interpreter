package main.lisp.scanner.tokens;

public class IntegerToken extends AbstractToken {
	
	protected IntegerToken(String value) {
		super(value, TokenType.INTEGER);
	}

	@Override
	public String toString() {
		return "number: " + getValue();
	}
}
