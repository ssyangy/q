/**
 * 
 */
package q.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 高危代码,请勿修改
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class IdCreator {
	private static long counterStartTimestamp = newTimestamp();

	private static long baseTimestamp = getBaseTimestamp();

	private static long getBaseTimestamp() {
		Calendar baseCal = Calendar.getInstance();
		baseCal.set(2011, 0, 1, 0, 0, 0);
		return baseCal.getTimeInMillis();
	}

	public static void setBaseTimestamp(long base) {
		baseTimestamp = base;
	}

	private static int counter = 0;

	private static int counterLimit = 100;

	public static void setCounterLimit(int limit) {
		counterLimit = limit;
	}
	
	public static int getCounterLimit() {
		return counterLimit;
	}

	private static int nodeFlag = getNodeFlag();

	private static int getNodeFlag() {
		int flag = 0;
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			flag = Integer.valueOf(StringKit.split(ip, '.')[3]);
		} catch (UnknownHostException e) {
			throw new IllegalStateException();
		}
		return flag;
	}

	public static void setNodeFlag(int flag) {
		nodeFlag = flag;
	}

	private static int version = 1;

	public static void setVersion(int v) {
		version = v;
	}

	private IdCreator() {
	}

	private static final Lock counterLock = new ReentrantLock();

	/**
	 * 
	 * 
	 * @return
	 */
	public static long getLongId() {
		return getLongId(newTimestamp());
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static long getLongId(long timestamp) {
		counterLock.lock();
		try {
			if (counter >= counterLimit) { // if counter overflow, wait next millisecond
				while (timestamp <= counterStartTimestamp) {
					timestamp = newTimestamp();
				}
				counter = 0; // reset counter
				counterStartTimestamp = timestamp; // reset start timestamp
			}
			long id = (((counterStartTimestamp - baseTimestamp) * counterLimit + counter++) * 1000 + nodeFlag) * 10 + version;
			return id;
		} finally {
			counterLock.unlock();
		}
	}

	private static long newTimestamp() {
		return System.currentTimeMillis();
	}

	/**
	 * FIXME can do more check
	 * 
	 * @param ids
	 * @return
	 */
	public static boolean isValidIds(long... ids) {
		for (long id : ids) {
			if (id <= 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * FIXME
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isNotValidId(long id) {
		if (id <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * FIXME
	 * 
	 * @param ids
	 * @return
	 */
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

	/**
	 * FIXME
	 * 
	 * @param ids
	 * @return
	 * @throws IllegalArgumentException
	 */
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
