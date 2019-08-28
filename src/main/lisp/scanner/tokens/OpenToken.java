package main.lisp.scanner.tokens;

public class OpenToken extends AbstractToken {

	public OpenToken(String value) {
		super("(", TokenType.OPEN);
	}

	@Override
	public String toString() {
		return "open: (";
	}
}
