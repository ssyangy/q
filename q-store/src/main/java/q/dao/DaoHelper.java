/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.dao.page.MessagePage;
import q.dao.page.WeiboPage;
import q.dao.page.WeiboReplyPage;
import q.domain.Message;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.util.CollectionKit;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class DaoHelper {

	public static List<WeiboReply> getPageWeiboReplyWithSenderRealName(PeopleDao peopleDao, WeiboDao weiboDao, WeiboReplyPage page) throws SQLException {
		List<WeiboReply> replies = weiboDao.getPageWeiboReply(page);
		if (CollectionKit.isNotEmpty(replies)) {
			Set<Long> senderIds = new HashSet<Long>(replies.size());
			for (WeiboReply reply : replies) {
				senderIds.add(reply.getSenderId());
			}
			Map<Long, String> map = peopleDao.getPeopleIdRealNameMapByIds(senderIds);
			for (WeiboReply reply : replies) {
				reply.setSenderRealName(map.get(reply.getSenderId()));
			}
		}
		return replies;
	}

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public static List<Weibo> getPageGroupWeiboWithSenderRealName(PeopleDao peopleDao, WeiboDao weiboDao, WeiboPage page) throws SQLException {
		List<Weibo> weibos = weiboDao.getPageGroupWeibo(page);
		if (CollectionKit.isNotEmpty(weibos)) {
			HashSet<Long> senderIds = new HashSet<Long>(weibos.size());
			for (Weibo weibo : weibos) {
				senderIds.add(weibo.getSenderId());
			}
			Map<Long, String> map = peopleDao.getPeopleIdRealNameMapByIds(senderIds);
			for (Weibo weibo : weibos) {
				weibo.setSenderRealName(map.get(weibo.getSenderId()));
			}
		}
		return weibos;
	}

	public static List<Weibo> getPageWeiboWithSenderRealName(PeopleDao peopleDao, WeiboDao weiboDao, WeiboPage page) throws SQLException {
		List<Weibo> weibos = weiboDao.getPageWeibo(page);
		if (CollectionKit.isNotEmpty(weibos)) {
			HashSet<Long> senderIds = new HashSet<Long>(weibos.size());
			for (Weibo weibo : weibos) {
				senderIds.add(weibo.getSenderId());
			}
			Map<Long, String> map = peopleDao.getPeopleIdRealNameMapByIds(senderIds);
			for (Weibo weibo : weibos) {
				weibo.setSenderRealName(map.get(weibo.getSenderId()));
			}
		}
		return weibos;
	}

	public static List<Message> getPageMessageRealName(PeopleDao peopleDao, MessageDao messageDao, MessagePage page) throws SQLException {
		List<Message> messages = messageDao.getPageMessages(page);
		if (CollectionKit.isNotEmpty(messages)) {
			HashSet<Long> peopleIds = new HashSet<Long>(messages.size() + 1); // people number is less than messages count + 1
			for (Message message : messages) {
				peopleIds.add(message.getSenderId());
				peopleIds.add(message.getReceiverId());
			}
			Map<Long, String> map = peopleDao.getPeopleIdRealNameMapByIds(peopleIds);
			for (Message message : messages) {
				message.setSenderRealName(map.get(message.getSenderId()));
				message.setReceiverRealName(map.get(message.getReceiverId()));
			}
		}
		return messages;
	}

}
