/**
 * 
 */
package q.serialize.performance;

import java.util.Random;

/**
 * @author xalinx at gmail dot com
 * @date Apr 6, 2010
 */
public abstract class AbstractMultiThreadLoad {

	protected static void load(final AbstractTask task, final int threads,
			final int times, final int randomSleep, final int fixSleep) throws Exception {
		Thread[] ths = new Thread[threads];
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(new Runnable() {
				public void run() {
					Random random = new Random();
					for (int j = 0; j < times; j++) {
						try {
							Object[] reqs = task.convertRequest();
							Object rs = task.convertResponse();
							if (j % 10000 == 0) {
								System.out.println(reqs);
								System.out.println(rs);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (randomSleep != 0) {
							try {
								Thread.sleep(random.nextInt(randomSleep) + fixSleep);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}, "simplepojo-load-" + i);
		}
		long start = System.currentTimeMillis();
		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			th.join();
		}
		System.out.println("waste:" + (System.currentTimeMillis() - start));
	}

	protected static void load(final AbstractTask task, final int times,
			final int randomSleep, final int fixSleep) throws Exception {
		Thread.currentThread().setName("simplepojo-load");
		long start = System.currentTimeMillis();
		Random random = new Random();
		for (int j = 0; j < times; j++) {
			Object[] reqs = task.convertRequest();
			Object rs = task.convertResponse();
			if (j % 10000 == 0) {
				System.out.println(reqs);
				System.out.println(rs);
			}
			if (randomSleep != 0) {
				Thread.sleep(random.nextInt(randomSleep) + fixSleep);
			}
		}
		System.out.println("waste:" + (System.currentTimeMillis() - start));
	}
}
