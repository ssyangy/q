package q.util;

import java.util.Map;

/**
 * 使用 Java 远程代码生成 ThreadDump. 适用于 JDK 1.5+. 参考: {@link ThreadgetStackTrace()}
 * {@link ThrowablegetStackTrace()}
 * 
 * @see StackTraceElement
 * @author beansoft@126.com 转载请注明出处: beansoft.blogjava.net
 */
public class SystemKit {
	/**
	 * 生成并返回 Thread Dump.
	 * 
	 * @return
	 */
	public static String treadDump() {
		StringBuilder output = new StringBuilder(1024);
		for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
			appendThreadStackTrace(output, (Thread) stackTrace.getKey(), (StackTraceElement[]) stackTrace.getValue());
		}
		return output.toString();
	}

	/**
	 * 处理并输出堆栈信息.
	 * 
	 * @param output
	 *            输出内容
	 * @param thread
	 *            线程
	 * @param stack
	 *            线程堆栈
	 */
	private static void appendThreadStackTrace(StringBuilder output, Thread thread, StackTraceElement[] stack) {
		// 忽略当前线程的堆栈信息
		if (thread.equals(Thread.currentThread())) {
			return;
		}
		output.append(thread).append(":").append(thread.getState()).append("\n");
		for (StackTraceElement element : stack) {
			output.append("\t").append(element).append("\n");
		}
	}

}