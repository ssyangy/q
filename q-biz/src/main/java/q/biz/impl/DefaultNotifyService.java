/**
 *
 */
package q.biz.impl;

import q.biz.NotifyService;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.log.Logger;
import redis.clients.jedis.Jedis;

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

	public void init() {
		pubJedis = new Jedis(pubHost, pubPort, pubTimeout);
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
		} catch (Exception e) {
			log.error("", e);
			this.init();
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
		} catch (Exception e) {
			log.error("", e);
			this.init();
		}
	}

}
