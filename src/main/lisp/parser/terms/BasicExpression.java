package main.lisp.parser.terms;

import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.BuiltinOperationManagerSingleton;

public class BasicExpression implements SExpression {
	private final SExpression head;
	private final SExpression tail;
	

	public BasicExpression() {
		this(new NilAtom(), new NilAtom());
	}
	
	public BasicExpression(SExpression head) {
		this(head, new NilAtom());
	}
	
	public BasicExpression(SExpression head, SExpression tail) {
		this.head = head;
		this.tail = tail;
	}
	
	@Override
	public SExpression eval() {
		if (head instanceof IdentifierAtom) {
			String operator = ((IdentifierAtom)head).getValue();
			Evaluator eval = BuiltinOperationManagerSingleton.get().getEvaluator(operator);
			if (eval == null) {
				System.err.println("No evaluator registered for operator '" + operator + "'");
			}
			return eval.eval(this);
		} else if (tail instanceof NilAtom) {
			return head.eval();
		} else {
			System.err.println("Expression does not start with an operator");
		}
		return null;
	}
	
	@Override
	public SExpression getHead() {
		return head;
	}

	@Override
	public SExpression getTail() {
		return tail;
	}
}
