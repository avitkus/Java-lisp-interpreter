package main.lisp.evaluator.lazy.string;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.evaluator.string.StrlenEvaluator;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.scanner.tokens.IntegerToken;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class LazyStrlenEvaluator extends StrlenEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'strlen'");
		}
		if (!(expr.getTail() instanceof NilAtom))  {
			throw new IllegalStateException("Too many arguments for operator 'strlen'");
		}
		
		SExpression evaled = expr.getHead().lazyEval(environment);
		if (evaled instanceof Thunk) {
			evaled = evaled.eval(environment);
		}
		
		if (!(evaled instanceof StringAtom)) {
			throw new IllegalStateException("Argument for operator 'strlen' must be String");
		}
		StringAtom strAtom = (StringAtom) evaled;
		
		String str = strAtom.getValue();
		
		IntegerToken tok = (IntegerToken)TokenFactory.newInstance(TokenType.INTEGER, ""+str.length());

		return new IntegerAtom(tok);
	}

}
