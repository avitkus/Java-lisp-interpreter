package main.lisp.scanner.tokens;

public abstract class AbstractToken implements Token {
	private final String value;
	private final String sType;
	private final TokenType type;
	
	protected AbstractToken(String value, TokenType type) {
		this.value = value;
		this.sType = type.name();
		this.type = type;
	}

	protected AbstractToken(String value, String type) {
		this.value = value;
		this.sType = type;
		this.type = TokenType.OTHER;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public String getTypeString() {
		return sType;
	}
	
	@Override
	public TokenType getType() {
		return type;
	}
}
