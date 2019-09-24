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
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'concatenate'");
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
		if (base instanceof NilAtom || base.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'concatenate'");
		}
		if (!(base.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'concatenate'");
		}
		SExpression str1Atom = base.getHead().eval(environment);
		SExpression str2Atom = base.getTail().getHead().eval(environment);
		if (!(str1Atom instanceof StringAtom) || !(str2Atom instanceof StringAtom)) {
			throw new IllegalStateException("Arguments for operator 'concatenate' must be (quote type) a1 a2 or String String");
		}

		String str1 = ((StringAtom)str1Atom).getValue();
		String str2 = ((StringAtom)str2Atom).getValue();
		
		StringToken tok = (StringToken)TokenFactory.newInstance(TokenType.STRING, str1+str2);
		
		return new StringAtom(tok);
	}

}
