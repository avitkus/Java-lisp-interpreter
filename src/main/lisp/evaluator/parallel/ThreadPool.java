package main.lisp.evaluator.parallel;

public interface ThreadPool {
	public void forkAll(Runnable... runnables);
	public void finish(ThreadPoolWorker runnable);
	public void quit();
}
