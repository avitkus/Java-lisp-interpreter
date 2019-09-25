package main.lisp.evaluator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import util.trace.Tracer;

public class BasicOperationManager implements OperationManager {
	private final Map<String, Evaluator> evaluatorMap;
	
	protected BasicOperationManager() {
		evaluatorMap = new HashMap<>();
	}
	
	@Override
	public void registerEvaluator(String name, Evaluator evaluator) {
		Tracer.info(this, "Registering evaluator " + evaluator + " for " + name);
		evaluatorMap.put(name.toUpperCase(), evaluator);
	}
	
	@Override
	public Evaluator getEvaluator(String name) {
		return evaluatorMap.get(name.toUpperCase());
	}

	@Override
	public Map<String, Evaluator> getEvaluators() {
		return Collections.unmodifiableMap(evaluatorMap);
	}

	@Override
	public boolean removeEvaluator(String name) {
		return evaluatorMap.remove(name) != null;
	}
}
