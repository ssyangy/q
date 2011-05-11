/**
 * 
 */
package q.biz.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import q.biz.NotifyService;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.log.Logger;
import redis.clients.jedis.Jedis;
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

	private Jedis pubJedis;
	private Lock resetPubJedisLock = new ReentrantLock();

	public void init() {
		pubJedis = createPubJedis();
	}

	/**
	 * @return
	 */
	private Jedis createPubJedis() {
		return new Jedis(pubHost, pubPort, pubTimeout);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyWeiboReply(q.domain.WeiboReply)
	 */
	@Override
	public void notifyWeiboReply(WeiboReply reply) {
		try {
			Long rs = pubJedis.publish("weiboReply", reply.getQuoteSenderId() + " " + reply.getContent());
			if (log.isDebugEnabled()) {
				log.debug("rs:" + rs);
			}
		} catch (JedisConnectionException e) {
			this.resetPubJedis();
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyWeibo(q.domain.Weibo)
	 */
	@Override
	public void notifyWeibo(Weibo weibo) {
		try {
			Long rs = pubJedis.publish("weibo", weibo.getSenderId() + " " + weibo.getContent());
			if (log.isDebugEnabled()) {
				log.debug("rs:" + rs);
			}
		} catch (JedisConnectionException e) {
			this.resetPubJedis();
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * reset jedis
	 */
	private void resetPubJedis() {
		if (this.resetPubJedisLock.tryLock()) {// do nothing if lock not free
			this.resetPubJedisLock.lock();
			try {
				Jedis tmp = this.createPubJedis();
				if (this.pubJedis != null) {
					this.pubJedis.disconnect();
				}
				this.pubJedis = tmp;
				log.error("resetPubJedis:" + this.pubJedis);
			} catch (Exception e) {
				log.error("resetPubJedis:", e);
			} finally {
				this.resetPubJedisLock.unlock();
			}
		}
	}

}
