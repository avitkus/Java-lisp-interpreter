package main.lisp.evaluator.parallel.pool;

public final class NullThreadPool extends AbstractThreadPool {

	@Override
	public void forkAll(Runnable... runnables) {
		
	}

	@Override
	public void finish(ThreadPoolWorker runnable) {
		
	}

	@Override
	public void quit() {
		
	}

}
