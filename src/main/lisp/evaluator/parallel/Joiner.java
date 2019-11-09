package main.lisp.evaluator.parallel;

public interface Joiner {
	public void finished();
	public void join() throws InterruptedException;
}
