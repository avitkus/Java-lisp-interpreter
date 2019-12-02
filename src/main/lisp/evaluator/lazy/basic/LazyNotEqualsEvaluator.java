package main.lisp.evaluator.lazy.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.basic.NotEqualsEvaluator;
import main.lisp.evaluator.lazy.Thunk;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class LazyNotEqualsEvaluator extends NotEqualsEvaluator {

	@Override
	public SExpression lazyEval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator '/='");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator '/='");
		}
		
		SExpression firstEvaled = expr.getHead().lazyEval(environment);
		SExpression secondEvaled = expr.getTail().getHead().lazyEval(environment);
		
		if (firstEvaled instanceof Thunk) {
			firstEvaled = firstEvaled.eval(environment);
		}
		if (secondEvaled instanceof Thunk) {
			secondEvaled = secondEvaled.eval(environment);
		}
		
		IntegerAtom firstInt = null;
		IntegerAtom secondInt = null;
		DecimalAtom firstDec = null;
		DecimalAtom secondDec = null;
		
		int correctArgs = 0;
		
		if (firstEvaled instanceof IntegerAtom) {
			firstInt = (IntegerAtom)firstEvaled;
			correctArgs++;
		}
		if (firstEvaled instanceof DecimalAtom) {
			firstDec = (DecimalAtom)firstEvaled;
			correctArgs++;
		}
		
		if (secondEvaled instanceof IntegerAtom) {
			secondInt = (IntegerAtom)secondEvaled;
			correctArgs++;
		}
		if (secondEvaled instanceof DecimalAtom) {
			secondDec = (DecimalAtom)secondEvaled;
			correctArgs++;
		}
		
		if (correctArgs != 2) {
			throw new IllegalStateException("Arguments for operator '/=' must both evaluate to numbers");
		}
		
		boolean notEqual;
		
		if (firstInt != null) {
			if (secondInt != null) {
				notEqual = firstInt.getValue().longValue() != secondInt.getValue().longValue();
			} else {
				notEqual = firstInt.getValue().longValue() != secondDec.getValue().doubleValue();
			}
		} else {
			if (secondInt != null) {
				notEqual = firstDec.getValue().doubleValue() != secondInt.getValue().longValue();
			} else {
				notEqual = firstDec.getValue().doubleValue() != secondDec.getValue().doubleValue();
			}
		}
			
		if (notEqual) {
			return new TAtom();
		} else {
			return new NilAtom();
		}
	}

}
