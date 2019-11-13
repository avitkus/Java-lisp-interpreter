package main.util.parallel;

public interface MutableJoiner extends Joiner {
	public void setNumThreads(int count);
}
