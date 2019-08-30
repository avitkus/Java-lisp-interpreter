package main.lisp.evaluator;

import java.util.HashMap;
import java.util.Map;

public class BasicOperationManager implements OperationManager {
	private final Map<String, Evaluator> evaluatorMap;
	
	public BasicOperationManager() {
		evaluatorMap = new HashMap<>();
	}
	
	public void registerEvaluator(String name, Evaluator evaluator) {
		evaluatorMap.put(name.toUpperCase(), evaluator);
	}
	
	public Evaluator getEvaluator(String name) {
		return evaluatorMap.get(name.toUpperCase());
	}
}
