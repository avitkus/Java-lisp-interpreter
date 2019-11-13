package main.util.parallel;

public interface Joiner {
	public void finished();
	public void join() throws InterruptedException;
}
