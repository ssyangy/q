/**
 * 
 */
package q.biz.impl;

import q.biz.CacheService;
import q.biz.PasswordResetToken;
import q.util.StringKit;
import redis.clients.jedis.Jedis;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public class DefaultCacheService implements CacheService {
	private String cacheHost;

	private int cachePort;

	private int cacheTimeout;

	private Jedis cacheJedis;

	public void setCacheHost(String cacheHost) {
		this.cacheHost = cacheHost;
	}

	public void setCachePort(int cachePort) {
		this.cachePort = cachePort;
	}

	public void setCacheTimeout(int cacheTimeout) {
		this.cacheTimeout = cacheTimeout;
	}

	public void init() {
		cacheJedis = new Jedis(cacheHost, cachePort, cacheTimeout);
	}

	private int passwordResetTokenVersion = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.CacheService#putPasswordResetToken(java.lang.String, long, long)
	 */
	@Override
	public void putPasswordResetToken(String key, long peopleId, long timestamp) {
		this.cacheJedis.set(key, passwordResetTokenVersion + ":" + peopleId + ":" + timestamp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.CacheService#getPasswordResetToken(java.lang.String)
	 */
	@Override
	public PasswordResetToken getPasswordResetToken(String key) {
		String value = this.cacheJedis.get(key);
		if (value == null) {
			return null;
		}
		String[] segs = StringKit.split(value, ':');
		PasswordResetToken token = null;
		if (Integer.valueOf(segs[0]) == passwordResetTokenVersion) {
			long peopleId = Long.valueOf(segs[1]);
			long timestamp = Long.valueOf(segs[2]);
			if (Math.abs(timestamp - System.currentTimeMillis()) <= 48 * 3600 * 1000L) { // token expired after 48 hours
				token = new PasswordResetToken(peopleId, timestamp);
			} else {
				this.removePasswordResetToken(key);// remove if token expired
			}
		}
		return token;
	}

	/* (non-Javadoc)
	 * @see q.biz.CacheService#removePasswordResetToken(java.lang.String)
	 */
	@Override
	public void removePasswordResetToken(String key) {
		this.cacheJedis.del(key);
	}

}
