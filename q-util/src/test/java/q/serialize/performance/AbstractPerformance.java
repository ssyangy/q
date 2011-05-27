/**
 * 
 */
package q.serialize.performance;


/**
 * @author xalinx at gmail dot com
 * @date Apr 6, 2010
 */
public abstract class AbstractPerformance {

	protected static void performanceResponse(final AbstractTask task,
			final int times) throws Exception {
		task.convertResponse();
		task.convertResponse();
		long start = System.currentTimeMillis();
		for (int j = 0; j < times; j++) {
			Object load = task.convertResponse();
			if (j % 10000 == 0) {
				System.out.println(load);
			}
		}
		System.out.println("convert response " + times + " times waste:"
				+ (System.currentTimeMillis() - start));
	}

	protected static void performanceRequest(final AbstractTask task,
			final int times) throws Exception {
		task.convertRequest();
		task.convertRequest();
		long start = System.currentTimeMillis();
		for (int j = 0; j < times; j++) {
			Object[] reqs = task.convertRequest();
			if (j % 10000 == 0) {
				System.out.println(reqs[0]);
			}
		}
		System.out.println("convert request " + times + " times waste:"
				+ (System.currentTimeMillis() - start));
	}
}
