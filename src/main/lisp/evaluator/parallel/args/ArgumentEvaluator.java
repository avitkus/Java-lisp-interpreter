package main.lisp.evaluator.parallel.args;

import java.util.ArrayList;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public interface ArgumentEvaluator {

	public static List<SExpression> split(SExpression expr) {
		List<SExpression> parts = new ArrayList<>();
		while (!(expr instanceof NilAtom)) {
			parts.add(expr.getHead());
			expr = expr.getTail();
		}
		return parts;
	}

	public List<SExpression> evaluate(List<SExpression> expressions, Environment env);
}
