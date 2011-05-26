/**
 * 
 */
package q.biz.impl;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

import q.biz.CacheService;
import q.biz.PasswordResetToken;
import q.log.Logger;
import q.util.StringKit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public class DefaultCacheService implements CacheService {
	private static final Logger log = Logger.getLogger();

	private String cacheHost;

	private int cachePort;

	private int cacheTimeout;

	public void setCacheHost(String cacheHost) {
		this.cacheHost = cacheHost;
	}

	public void setCachePort(int cachePort) {
		this.cachePort = cachePort;
	}

	public void setCacheTimeout(int cacheTimeout) {
		this.cacheTimeout = cacheTimeout;
	}

	private JedisPool pool;

	public void init() {
		pool = new JedisPool(new Config(), cacheHost, cachePort, cacheTimeout);
	}

	private int passwordResetTokenVersion = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.CacheService#putPasswordResetToken(java.lang.String, long, long)
	 */
	@Override
	public void putPasswordResetToken(String key, long peopleId, long timestamp) {
		Jedis jedis = pool.getResource();
		try {
			jedis.set(key, passwordResetTokenVersion + ":" + peopleId + ":" + timestamp);
		} catch (JedisConnectionException e) {
			log.error("putPasswordResetToken", e);
			if (jedis != null) {
				jedis.disconnect();
			}
		} catch (Exception e) {
			log.error("putPasswordResetToken", e);
		} finally {
			pool.returnResource(jedis);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.CacheService#getPasswordResetToken(java.lang.String)
	 */
	@Override
	public PasswordResetToken getPasswordResetToken(String key) {
		Jedis jedis = pool.getResource();
		String value = null;
		try {
			value = jedis.get(key);
		} catch (JedisConnectionException e) {
			log.error("getPasswordResetToken", e);
			if (jedis != null) {
				jedis.disconnect();
			}
		} catch (Exception e) {
			log.error("getPasswordResetToken", e);
		} finally {
			pool.returnResource(jedis);
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.CacheService#removePasswordResetToken(java.lang.String)
	 */
	@Override
	public void removePasswordResetToken(String key) {
		Jedis jedis = pool.getResource();
		try {
			jedis.del(key);
		} catch (JedisConnectionException e) {
			log.error("removePasswordResetToken", e);
			if (jedis != null) {
				jedis.disconnect();
			}
		} catch (Exception e) {
			log.error("removePasswordResetToken", e);
		} finally {
			pool.returnResource(jedis);
		}
	}

	/**
	 * 
	 */
	private static final String CHANNEL_WEIBO_REPLY = "weiboReply";

	/**
	 * 
	 */
	private static final String CHANNEL_MESSAGE = "message";

	/**
	 * 
	 */
	private static final String CHANNEL_AT = "at";

	/**
	 * 
	 */
	private static final String CHANNEL_FO = "fo";

	@Override
	public void clearMessageNotify(long peopleId) {
		this.clearPeopleNotify(CHANNEL_MESSAGE, peopleId, "clearMessageNotify");
	}

	@Override
	public void clearWeiboReplyNotify(long peopleId) {
		this.clearPeopleNotify(CHANNEL_WEIBO_REPLY, peopleId, "clearWeiboReply");
	}

	@Override
	public void clearFoNotify(long peopleId) {
		this.clearPeopleNotify(CHANNEL_FO, peopleId, "clearFoNotify");
	}

	@Override
	public void clearAtNotify(long peopleId) {
		this.clearPeopleNotify(CHANNEL_AT, peopleId, "clearAtNotify");
	}

	public void clearPeopleNotify(String channel, long peopleId, String error) {
		Jedis jedis = pool.getResource();
		try {
			jedis.hdel(channel, "" + peopleId);
		} catch (JedisConnectionException e) {
			log.error(error, e);
			if (jedis != null) {
				jedis.disconnect();
			}
		} catch (Exception e) {
			log.error(error, e);
		} finally {
			pool.returnResource(jedis);
		}
	}

}
