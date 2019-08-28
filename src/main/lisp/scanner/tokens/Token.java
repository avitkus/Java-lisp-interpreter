package main.lisp.scanner.tokens;

public interface Token {
	public String getValue();
	public String getTypeString();
	public TokenType getType();
}
