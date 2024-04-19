package main.lisp.parser.terms;

public class QuoteAtomFactory {
	public static QuoteAtom newInstance(SExpression expr) {
		return new QuoteAtom(expr);
	}	
}
