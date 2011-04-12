/**
 * 
 */
package q.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class IdCreator {
	private static final AtomicInteger counter = new AtomicInteger();

	private static final long base;

	static {
		Calendar baseCal = Calendar.getInstance();
		baseCal.set(Calendar.YEAR, 2011);
		base = baseCal.getTime().getTime();
	}

	/**
	 * 
	 * FIXME add ip id for distribution
	 * 
	 * @return
	 */
	public synchronized static long getLongId() {
		return (System.currentTimeMillis() - base) * 1000 + counter.getAndIncrement();
	}

	public static boolean isValidIds(long... ids) {
		for (long id : ids) {
			if (id <= 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidIds(String... ids) {
		for (String id : ids) {
			try {
				if (Long.valueOf(id) <= 0) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	public static Set<Long> convertIfValidIds(String... ids) throws IllegalArgumentException {
		Set<Long> result = new HashSet<Long>(ids.length);
		for (String id : ids) {
			try {
				Long convert = Long.valueOf(id);
				if (convert <= 0) {
					throw new IllegalArgumentException(id);
				}
				result.add(convert);
			} catch (Exception e) {
				throw new IllegalArgumentException(id);
			}
		}
		return result;
	}
}
