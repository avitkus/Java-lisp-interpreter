package main.lisp.evaluator.parallel;

public final class NullThreadPool extends AbstractThreadPool {

	@Override
	public void fork(Runnable runnable) {
		
	}

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
