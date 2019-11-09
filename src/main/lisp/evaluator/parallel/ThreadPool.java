package main.lisp.evaluator.parallel;

public interface ThreadPool {
	public void fork(Runnable runnable);
	public void forkAll(Runnable... runnables);
	public void finish(ThreadPoolWorker runnable);
	public void quit();
}
