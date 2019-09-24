package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class IdentifierAtom extends AbstractAtom<String> {

	public IdentifierAtom(Token token) {
		super(token);
	}

	public IdentifierAtom(String string) {
		this(TokenFactory.newInstance(TokenType.IDENTIFIER, string));
	}

	@Override
	public String getValue() {
		return token.getValue();
	}
}
