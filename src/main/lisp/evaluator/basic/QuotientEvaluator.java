package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;

public class QuotientEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom || expr.getTail() instanceof NilAtom) {
//			System.err.println("Missing arguments for operator '/'");
			throw new IllegalStateException("Missing arguments for operator '/'");
		}
		
		SExpression firstEvaled = expr.getHead().eval(environment);
		SExpression secondEvaled = expr.getTail().eval(environment);
		
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
//			System.err.println("Arguments for operator '/' must both evaluate to numbers");
			throw new IllegalStateException("Arguments for operator '/' must both evaluate to numbers");
		}
		
		if (firstInt != null && secondInt != null) {
			long quotient = firstInt.getValue() / secondInt.getValue();
			return new IntegerAtom(quotient);
		} else {
			double quotient = 0;
			if (firstInt != null) {
				quotient = firstInt.getValue();
			} else if (firstDec != null) {
				quotient = firstDec.getValue();
			}
			if (secondInt != null) {
				quotient /= secondInt.getValue();
			} else if (secondDec != null) {
				quotient /= secondDec.getValue();
			}
			return new DecimalAtom(quotient);
		}
	}

}
