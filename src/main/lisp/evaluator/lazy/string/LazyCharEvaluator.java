package main.lisp.evaluator.lazy.string;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.evaluator.string.CharEvaluator;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.scanner.tokens.StringToken;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class LazyCharEvaluator extends CharEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'char'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom))  {
			throw new IllegalStateException("Too many arguments for operator 'char'");
		}
		
		SExpression evaled = expr.getHead().lazyEval(environment);
		if (evaled instanceof Thunk) {
			evaled = evaled.eval(environment);
		}
		
		if (!(evaled instanceof StringAtom)) {
			throw new IllegalStateException("Arguments for operator 'char' must be String, Integer");
		}
		StringAtom strAtom = (StringAtom) evaled;

		evaled = expr.getTail().getHead().lazyEval(environment);
		if (evaled instanceof Thunk) {
			evaled = evaled.eval(environment);
		}
		
		if (!(evaled instanceof IntegerAtom)) {
			throw new IllegalStateException("Arguments for operator 'char' must be String, Integer");
		}
		IntegerAtom idxAtom = (IntegerAtom) evaled;
		
		String str = strAtom.getValue();
		long idx = idxAtom.getValue();
		
		if (idx >= str.length()) {
			throw new IllegalStateException("Index " + idx + " greater than length of string");
		}
		
		StringToken tok = (StringToken)TokenFactory.newInstance(TokenType.STRING, ""+str.charAt((int) idx));
		
		return new StringAtom(tok);
	}

}
