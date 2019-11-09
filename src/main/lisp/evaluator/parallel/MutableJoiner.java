package main.lisp.evaluator.parallel;

public interface MutableJoiner extends Joiner {
	public void setNumThreads(int count);
}
