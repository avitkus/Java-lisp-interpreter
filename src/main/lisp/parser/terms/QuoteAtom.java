package main.lisp.parser.terms;

import main.lisp.evaluator.Environment;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class QuoteAtom extends AbstractAtom<String> {
	private final SExpression expr;
	
	public QuoteAtom(SExpression expr) {
		super(TokenFactory.newInstance(TokenType.IDENTIFIER, expr.toString()));
		this.expr = expr;
	}
	
	@Override
	public SExpression eval(Environment environment) {
		return expr;
	}

	@Override
	public String toString() {
		return "(quote " + getValue() + ")";
	}

	@Override
	public String getValue() {
		return token.getValue();
	}
}
