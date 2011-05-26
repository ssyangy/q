/**
 * 
 */
package q.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import q.log.Logger;

/**
 * 高危代码,请勿修改
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class IdCreator {
	private static final Logger log = Logger.getLogger();
	private static long counterStartTimestamp = newTimestamp();

	private static long baseTimestamp = getBaseTimestamp();

	/**
	 * 18个9是很难达到的ID
	 */
	public static final long MAX_ID = 999999999999999999L;

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

	private static int nodeFlag;

	static {
		String ip = null;
		Socket socket = null;
		try {
			// NetworkInterface eth1 = NetworkInterface.getByName("eth1");//
			// 张江机房的内网ip网卡,e.g:eth1:172.16.40.4 eth0:222.73.29.151
			// NetworkInterface eth0 = NetworkInterface.getByName("eth0");//
			// 测试机没有eth1
			// if (eth1 != null) {
			// ip = eth1.getInetAddresses().nextElement().getHostAddress();
			// } else if (eth0 != null) {
			// ip = eth0.getInetAddresses().nextElement().getHostAddress();
			// } else {
			// ip = InetAddress.getLocalHost().getHostAddress(); // 开发机
			// }
			socket = new Socket("www.baidu.com", 80);
			ip = socket.getLocalAddress().getHostAddress();
		} catch (IOException e) {
			log.error("can't get local address", e);
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				log.error("can't get local address", e);
			}
		}
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("can't get local address", e);
		}
		nodeFlag = getIpLastSeg(ip);
		log.warn("create id using ip:%s,nodeFlag:%s", ip, nodeFlag);
	}

	public static int getNodeFlag() {
		return nodeFlag;
	}

	private static int getIpLastSeg(String ip) {
		return Integer.valueOf(StringKit.split(ip, '.')[3]);
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
	private static long getLongId(long timestamp) {
		counterStartTimestamp = timestamp; // reset start timestamp
		counterLock.lock();
		try {
			// if counter overflow, reset
			if (counter >= counterLimit) {
				counter = 0; // reset counter
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
