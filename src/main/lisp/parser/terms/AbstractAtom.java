package main.lisp.parser.terms;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.scanner.tokens.Token;
import util.trace.Tracer;

public abstract class AbstractAtom<T> extends AbstractSExpression implements Atom<T> {
	protected final Token token;
	
	public AbstractAtom(Token token) {
		this.token = token;
		traceConstruction();
	}
	
	protected void traceConstruction() {
		Tracer.info(this, this.getClass().getSimpleName() + "(" + token + ")");
	}
	
	@Override
	public SExpression eval(Environment environment) {
		SExpression ret = doEval(environment);

		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		Tracer.info(this, "Eval: " + this + " --> " + ret);
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
		return ret;
	}
	
	protected SExpression doEval(Environment environment) {
		return this;
	}
	
	@Override
	public SExpression lazyEval(Environment environment) {
		SExpression ret = doLazyEval(environment);

		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		Tracer.info(this, "Lazy eval: " + this + " --> " + ret);
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
		return ret;
	}
	
	protected SExpression doLazyEval(Environment environment) {
		return doEval(environment);
	}
	
	@Override
	public String toString() {
		return token.getValue();
	}
	
	@Override
	public boolean isList() {
		return false;
	}
	
	@Override
	public String toStringAsList() {
		return "";
	}
	
	@Override
	protected String toStringAsListHelper() {
		return toString();
	}
	
	@Override
	public String toStringAsSExpression() {
		return toString();
	}
	
	public String toStringAsSExpressionDeep() {
		return toStringAsSExpression();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Atom<?>)) {
			return false;
		} else {
			if (!o.getClass().equals(this.getClass())) {
				return false;
			} else {
				return ((Atom<?>)o).getValue().equals(getValue());
			}
		}
	}
	
	@Override
	public boolean isAtom() {
		return true;
	}
	
	@Override
	public boolean isNIL() {
		return false;
	}
}
