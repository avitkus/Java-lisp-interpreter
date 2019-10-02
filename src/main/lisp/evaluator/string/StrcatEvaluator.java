package main.lisp.evaluator.string;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.scanner.tokens.StringToken;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class StrcatEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'strcat'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom))  {
			throw new IllegalStateException("Too many arguments for operator 'strcat'");
		}
		
		SExpression evaled = expr.getHead().eval(environment);
		
		if (!(evaled instanceof StringAtom)) {
			throw new IllegalStateException("Arguments for operator 'strcat' must be String, String");
		}
		StringAtom str1Atom = (StringAtom) evaled;
		
		evaled = expr.getTail().getHead().eval(environment);
		if (!(evaled instanceof StringAtom)) {
			throw new IllegalStateException("Arguments for operator 'strcat' must be String, String");
		}
		StringAtom str2Atom = (StringAtom) evaled;
		
		String str1 = str1Atom.getValue();
		String str2 = str2Atom.getValue();
		
		StringToken tok = (StringToken)TokenFactory.newInstance(TokenType.STRING, str1+str2);
		
		return new StringAtom(tok);
	}

}
