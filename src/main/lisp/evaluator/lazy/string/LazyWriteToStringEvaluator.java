package main.lisp.evaluator.lazy.string;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.evaluator.string.WriteToStringEvaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.scanner.tokens.StringToken;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class LazyWriteToStringEvaluator extends WriteToStringEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'write-to-string'");
		}
		if (!(expr.getTail() instanceof NilAtom))  {
			throw new IllegalStateException("Too many arguments for operator 'write-to-string'");
		}
		
		SExpression evaled = expr.getHead().lazyEval(environment);
		if (evaled instanceof Thunk) {
			evaled = evaled.eval(environment);
		}
		
		if (!evaled.isAtom()) {
			throw new IllegalStateException("Argument for operator 'write-to-string' must be an atom");
		}
		
		StringToken tok = (StringToken)TokenFactory.newInstance(TokenType.STRING, evaled.toString());
		
		return new StringAtom(tok);
	}

}
