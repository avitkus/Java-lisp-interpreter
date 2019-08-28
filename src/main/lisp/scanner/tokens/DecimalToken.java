package main.lisp.scanner.tokens;

public class DecimalToken extends AbstractToken {
	
	public DecimalToken(String value) {
		super(value, TokenType.DECIMAL);
	}


	@Override
	public String toString() {
		return "number: " + getValue();
	}
}
