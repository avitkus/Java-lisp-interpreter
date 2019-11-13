package main.lisp.evaluator.parallel;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.IntegerToken;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;

public class NumThreadsEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		if (!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'numThreads'");
		}
		String count = Integer.toString(Thread.activeCount());
		IntegerToken tok = (IntegerToken)TokenFactory.newInstance(TokenType.INTEGER, count);
		return new IntegerAtom(tok);
	}

}
