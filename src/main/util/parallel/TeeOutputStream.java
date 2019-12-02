package main.util.parallel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TeeOutputStream extends OutputStream {
	private final List<OutputStream> streams;
	
	public TeeOutputStream() {
		streams = new ArrayList<>();
	}
	
	public void addStream(OutputStream stream) {
		streams.add(stream);
	}
	
	public void addStreams(OutputStream...outputStreams) {
		for(OutputStream stream : outputStreams) {
			addStream(stream);
		}
	}
	
	public void removeStream(OutputStream outputStream) {
		streams.remove(outputStream);
	}
	
	@Override
	public void write(int b) throws IOException {
		for(OutputStream stream : streams) {
			stream.write(b);
		}
	}

	@Override
	public void write(byte[] b) throws IOException {
		for(OutputStream stream : streams) {
			stream.write(b);
		}
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		for(OutputStream stream : streams) {
			stream.write(b, off, len);
		}
	}

	@Override
	public void flush() throws IOException {
		for(OutputStream stream : streams) {
			stream.flush();
		}
	}

	@Override
	public void close() throws IOException {
		for(OutputStream stream : streams) {
			stream.close();
		}
	}
}
