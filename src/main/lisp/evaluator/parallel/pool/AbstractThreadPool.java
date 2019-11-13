package main.lisp.evaluator.parallel.pool;

import util.trace.Tracer;

public abstract class AbstractThreadPool implements ThreadPool {

	private static final String WORKER_THREAD_NAME_PREFIX = "ThreadPoolWorker-";
	private int count = 0;
	
	protected String nextThreadName() {
		String name = WORKER_THREAD_NAME_PREFIX + count;
		count ++;
		Tracer.info(this, "New thread: " + name);
		return name;
	}
}
