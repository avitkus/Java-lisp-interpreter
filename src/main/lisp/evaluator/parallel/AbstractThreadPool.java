package main.lisp.evaluator.parallel;

public abstract class AbstractThreadPool implements ThreadPool {

	private static final String WORKER_THREAD_NAME_PREFIX = "ThreadPoolWorker-";
	private int count = 0;
	
	protected String nextThreadName() {
		return WORKER_THREAD_NAME_PREFIX + count++;
	}
}
