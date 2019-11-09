package main.lisp.evaluator.parallel;

public interface ThreadPoolWorker extends Runnable {
	public void setDynamicRunnable(Runnable dynamic, Joiner joiner);
	public boolean hasStarted();
	public void kill();
}
