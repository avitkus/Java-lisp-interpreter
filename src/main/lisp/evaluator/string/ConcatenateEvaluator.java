package main.lisp.evaluator.string;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.scanner.tokens.StringToken;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class ConcatenateEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			return processString(expr, environment);
		}
		
		SExpression arg1 = expr.getHead().eval(environment);
		
		if (arg1 instanceof IdentifierAtom) {
			String type = ((IdentifierAtom)arg1).getValue();
			switch (type) {
			case "STRING":
				return processString(expr.getTail(), environment);
			default:
				throw new IllegalStateException("Operator 'concatenate' is not defined for type '" + type + "'");
			}
		} else {
			return processString(expr, environment);
		}
	}
	
	private SExpression processString(SExpression base, Environment environment) {
		StringBuilder sb = new StringBuilder();
		
		while (!(base instanceof NilAtom)){
			SExpression strAtom = base.getHead().eval(environment);
			if (!(strAtom instanceof StringAtom)) {
				throw new IllegalStateException("Arguments for operator 'concatenate' must be (quote type) arg ... or String ...");
			}
			sb.append(((StringAtom)strAtom).getValue());
			base = base.getTail();
		}
		
		StringToken tok = (StringToken)TokenFactory.newInstance(TokenType.STRING, sb.toString());
		
		return new StringAtom(tok);
	}

}
