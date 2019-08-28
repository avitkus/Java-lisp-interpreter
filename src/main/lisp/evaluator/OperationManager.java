package main.lisp.evaluator;

public interface OperationManager {
	public void registerEvaluator(String name, Evaluator evaluator);
	
	public Evaluator getEvaluator(String name);
}
