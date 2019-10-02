package main.lisp.evaluator.string;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.scanner.tokens.StringToken;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class WriteToStringEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'write-to-string'");
		}
		if (!(expr.getTail() instanceof NilAtom))  {
			throw new IllegalStateException("Too many arguments for operator 'write-to-string'");
		}
		
		SExpression evaled = expr.getHead().eval(environment);
		
		if (!(evaled instanceof Atom)) {
			throw new IllegalStateException("Argument for operator 'write-to-string' must be an atom");
		}
		
		StringToken tok = (StringToken)TokenFactory.newInstance(TokenType.STRING, evaled.toString());
		
		return new StringAtom(tok);
	}

}
