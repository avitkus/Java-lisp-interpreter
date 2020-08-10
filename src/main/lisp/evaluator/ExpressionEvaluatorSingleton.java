package main.lisp.evaluator;

public class ExpressionEvaluatorSingleton {
	private static ExpressionEvaluator evaluator;
	
	public static void set(ExpressionEvaluator evaluator) {
		ExpressionEvaluatorSingleton.evaluator = evaluator;
	}
	
	public static ExpressionEvaluator get() {
		if (evaluator == null) {
			evaluator = ExpressionEvaluatorFactory.newInstance();
		}
		return evaluator;
	}
}
