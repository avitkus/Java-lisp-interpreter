package main.lisp.evaluator.lazy;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.SExpression;

public class BasicThunk implements Thunk {
	private final SExpression body;
	private final Environment env;
	private SExpression value;
	
	protected BasicThunk(SExpression body, Environment env) {
		this.body = body;
		this.env = env;
		value = null;
	}
	
	@Override
	public SExpression eval(Environment environment) {
		if (value == null) {
			value = body.lazyEval(env);
			while(value instanceof BasicThunk) {
				value = value.lazyEval(env);
			}
		}
		return value;
	}

	@Override
	public boolean isList() {
		if (value != null) {
			return value.isList();
		} else {
			return false;
		}
//		return eval(env).isList();
	}

	@Override
	public String toStringAsList() {
		if (LispInterpreterSettings.doesThunkPrintEval()) {
			return eval(env).toStringAsList();
		} else {
			return noEvalPrint();
		}
	}

	@Override
	public String toStringAsSExpression() {
		if (LispInterpreterSettings.doesThunkPrintEval()) {
			return eval(env).toStringAsSExpression();
		} else {
			return noEvalPrint();
		}
	}

	@Override
	public String toStringAsSExpressionDeep() {
		if (LispInterpreterSettings.doesThunkPrintEval()) {
			return eval(env).toStringAsSExpressionDeep();
		} else {
			return noEvalPrint();
		}
	}

	@Override
	public SExpression getValue() {
		return body;
	}
	
	@Override
	public String toString() {
		if (LispInterpreterSettings.doesThunkPrintEval()) {
			return eval(env).toString();
		} else {
			return noEvalPrint();
		}
	}
	
	private String noEvalPrint() {
		return "Thunk: [" + body + "]";
	}

	@Override
	public boolean isNIL() {
//		if (LispInterpreterSettings.isThunkMetaEvals()) {
//			return eval(env).isNIL();
//		} else 
		if (value != null) {
			return value.isNIL();
		} else {
			return false;
		}
	}

	@Override
	public boolean isAtom() {
//		if (LispInterpreterSettings.isThunkMetaEvals()) {
//			return eval(env).isAtom();
//		} else
		if (value != null) {
			return value.isAtom();
		} else {
			return false;
		}
	}
}
