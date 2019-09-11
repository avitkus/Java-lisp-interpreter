package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;

public class StringAtom extends AbstractAtom<String> {

	public StringAtom(Token token) {
		super(token);
	}

	@Override
	public String getValue() {
		return token.getValue();
	}
	
	@Override
	public String toString() {
		return "\"" + getValue() + "\"";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StringAtom)) {
			return false;
		}
		StringAtom s = (StringAtom)o;
		return s.getValue().equals(getValue());
	}
}
