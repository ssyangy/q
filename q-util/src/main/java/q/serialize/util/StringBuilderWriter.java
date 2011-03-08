/**
 * 
 */
package q.serialize.util;

import java.io.IOException;
import java.io.Writer;

/**
 * NTS
 * 
 * @author xalinx at gmail dot com
 * @date Apr 3, 2010
 */
public class StringBuilderWriter extends Writer {
	private StringBuilder buf;

	/**
	 * 
	 */
	public StringBuilderWriter() {
		this.buf = new StringBuilder();
	}

	/**
	 * @param lock
	 */
	public StringBuilderWriter(int initialSize) {
		if (initialSize < 0) {
			throw new IllegalArgumentException("Negative buffer size");
		}
		buf = new StringBuilder(initialSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Writer#close()
	 */
	@Override
	public void close() throws IOException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Writer#flush()
	 */
	@Override
	public void flush() throws IOException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Writer#write(char[], int, int)
	 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		buf.append(cbuf, off, len);
	}

	@Override
	public Writer append(char c) throws IOException {
		buf.append(c);
		return this;
	}

	@Override
	public Writer append(CharSequence csq, int start, int end)
			throws IOException {
		write(csq.subSequence(start, end).toString());
		return this;
	}

	@Override
	public Writer append(CharSequence csq) throws IOException {
		write(csq.toString());
		return this;
	}

	@Override
	public void write(char[] cbuf) throws IOException {
		buf.append(cbuf);
	}

	@Override
	public void write(int c) throws IOException {
		buf.append((char) c);
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		buf.append(str.substring(off, off + len));
	}

	@Override
	public void write(String str) throws IOException {
		buf.append(str);
	}

	public String toString() {
		return buf.toString();
	}

}
