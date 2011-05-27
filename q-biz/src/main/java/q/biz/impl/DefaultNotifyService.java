/**
 *
 */
package q.biz.impl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

import q.biz.NotifyService;
import q.domain.MessageReply;
import q.domain.PeopleRelation;
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
	/**
	 * 
	 */
	private static final String NOTIFY_SPLIT = " ";

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

	/**
	 * 
	 */
	private static final String CHANNEL_WEIBO = "weibo";

	/**
	 * 
	 */
	private static final String CHANNEL_GROUP = "group";

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyWeiboReply(q.domain.WeiboReply)
	 */
	@Override
	public void notifyWeiboReply(WeiboReply reply) {
		// notify quote sender if he isn't reply sender
		if (reply.getSenderId() != reply.getQuoteSenderId()) {
			this.notifyPeople(CHANNEL_WEIBO_REPLY, reply.getQuoteSenderId() + NOTIFY_SPLIT + reply.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyWeibo(q.domain.Weibo)
	 */
	@Override
	public void notifyWeibo(Weibo weibo) {
		this.notifyPeople(CHANNEL_WEIBO, weibo.getSenderId() + NOTIFY_SPLIT + weibo.getId());
	}

	@Override
	public void notifyGroupWeibo(long groupId, Weibo weibo) {
		this.notifyPeople(CHANNEL_GROUP, groupId + NOTIFY_SPLIT + weibo.getId());
	}

	@Override
	public void notifyMessageReply(MessageReply reply, Collection<Long> receiverIds, long loginId) {
		for (Iterator<Long> itt = receiverIds.iterator(); itt.hasNext();) {
			Long item = itt.next();
			if (item == loginId) {
				itt.remove();
			}
		}
		String receiversStr = StringUtils.join(receiverIds, ',');
		this.notifyPeople(CHANNEL_MESSAGE, receiversStr + NOTIFY_SPLIT + reply.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyPeopleFollowing(q.domain.PeopleRelation)
	 */
	@Override
	public void notifyPeopleFollowing(PeopleRelation relation) {
		this.notifyPeople(CHANNEL_FO, relation.getToPeopleId() + NOTIFY_SPLIT + relation.getFromPeopleId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.NotifyService#notifyAt(long, long)
	 */
	@Override
	public void notifyAt(long fromPeopleId, long toPeopleId) {
		this.notifyPeople(CHANNEL_AT, toPeopleId + NOTIFY_SPLIT + fromPeopleId);
	}

	private void notifyPeople(String channel, String content) {
		Jedis jedis = pool.getResource();
		try {
			jedis.publish(channel, content);
		} catch (JedisConnectionException e) {
			log.error(channel, e);
			if (jedis != null) {
				jedis.disconnect();
			}
		} catch (Exception e) {
			log.error(channel, e);
		} finally {
			pool.returnResource(jedis);
		}
	}
}
