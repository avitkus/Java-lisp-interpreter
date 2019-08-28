package main.lisp.scanner.tokens;

public class CloseToken extends AbstractToken {

	public CloseToken(String value) {
		super(")", TokenType.CLOSE);
	}

	@Override
	public String toString() {
		return "close: )";
	}
}
