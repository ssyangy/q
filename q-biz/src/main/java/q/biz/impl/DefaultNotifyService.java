/**
 *
 */
package q.biz.impl;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

import q.biz.NotifyService;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.log.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author seanlinwang at gmail dot com
 * @date May 5, 2011
 * 
 */
public class DefaultNotifyService implements NotifyService {
	private static final Logger log = Logger.getLogger();

	private String pubHost;

	private int pubPort;

	private int pubTimeout;

	public void setPubHost(String pubHost) {
		this.pubHost = pubHost;
	}

	public void setPubPort(int pubPort) {
		this.pubPort = pubPort;
	}

	public void setPubTimeout(int pubTimeout) {
		this.pubTimeout = pubTimeout;
	}

	private JedisPool pool;

	public void init() {
		pool = new JedisPool(new Config(), pubHost, pubPort, pubTimeout);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyWeiboReply(q.domain.WeiboReply)
	 */
	@Override
	public void notifyWeiboReply(WeiboReply reply) {
		Jedis jedis = pool.getResource();
		try {
			jedis.publish("weiboReply", reply.getQuoteSenderId() + " " + reply.getContent());
		} catch (JedisConnectionException e) {
			log.error("notifyWeiboReply", e);
			if (jedis != null) {
				jedis.disconnect();
			}
		} catch (Exception e) {
			log.error("notifyWeiboReply", e);
		} finally {
			pool.returnResource(jedis);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyWeibo(q.domain.Weibo)
	 */
	@Override
	public void notifyWeibo(Weibo weibo) {
		Jedis jedis = pool.getResource();
		try {
			jedis.publish("weibo", weibo.getSenderId() + " " + weibo.getContent());
		} catch (JedisConnectionException e) {
			log.error("notifyWeibo", e);
			if (jedis != null) {
				jedis.disconnect();
			}
		} catch (Exception e) {
			log.error("notifyWeibo", e);
		} finally {
			pool.returnResource(jedis);
		}
	}

}
