package main.lisp.evaluator.parallel.pool;

public interface ThreadPool {
	public void doParallel(Runnable... runnables);
	public void finish(ThreadPoolWorker runnable);
	public void quit();
	int getCount();
}
