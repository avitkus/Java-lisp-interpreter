package main.lisp.evaluator.parallel;

import main.util.parallel.Joiner;

public interface ThreadPoolWorker extends Runnable {
	public void setDynamicRunnable(Runnable dynamic, Joiner joiner);
	public boolean hasStarted();
	public void kill();
}
