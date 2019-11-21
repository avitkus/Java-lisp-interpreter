package main.lisp.evaluator.function;

import java.util.Arrays;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.AbstractAtom;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

public class BasicLambda extends AbstractAtom<String> implements Lambda {
	private final IdentifierAtom[] args;
	private final SExpression body;
	
	public BasicLambda(IdentifierAtom[] args, SExpression body) {
		super(null);
		this.args = args;
		this.body = body;
	}
	
	protected void traceConstruction() {
		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		Tracer.info(this, this.getClass().getSimpleName() + "(" + Arrays.toString(args) + ", " + body + ")");
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
	}

	@Override
	public String getValue() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("LAMBDA (");
		for(int i = 0; i < args.length; i ++) {
			if (i != 0) {
				sb.append(" ");
			}
			sb.append(args[i].getValue());
		}
		sb.append(")");
		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		if (body instanceof Atom) {
			sb.append(" ").append(body);
		} else {
			SExpression tmp = body;//.append(body);
			while (!(tmp instanceof NilAtom)) {
				sb.append(" ").append(tmp.getHead());
				tmp = tmp.getTail();
			}
		}
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
		
		return sb.toString();
	}
	
	@Override
	public String toStringAsSExpression() {
		return getValue();
	}
	
	@Override
	public String toString() {
		return getValue();
	}

	@Override
	protected SExpression doEval(Environment environment) {
		SExpression cur = body;
		SExpression ret = new NilAtom();
		
		while (!(cur instanceof NilAtom)) {
			ret = cur.getHead().eval(environment);
			cur = cur.getTail();
		}
		
		return ret;
	}


	@Override
	protected SExpression doLazyEval(Environment environment) {
		SExpression cur = body;
		SExpression ret = new NilAtom();
		
		while (!(cur instanceof NilAtom)) {
			ret = cur.getHead().lazyEval(environment);
			cur = cur.getTail();
		}
		
		return ret;
	}

	@Override
	public IdentifierAtom[] getArgumentNames() {
		return args;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Lambda)) {
			return false;
		} else {
			Lambda lambda = (Lambda)o;
			if (!body.equals(lambda)) {
				return false;
			}
			if (!Arrays.equals(args, lambda.getArgumentNames())) {
				return false;
			}
			return true;
		}
	}
}
