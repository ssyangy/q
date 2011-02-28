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
import q.domain.Group;
import q.domain.Message;
import q.domain.People;
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

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param groupDao
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public static List<WeiboReply> getPageWeiboReplyWithSenderRealNameAndFrom(PeopleDao peopleDao, WeiboDao weiboDao, GroupDao groupDao, WeiboReplyPage page) throws SQLException {
		List<WeiboReply> replies = weiboDao.getPageWeiboReply(page);
		if (CollectionKit.isNotEmpty(replies)) {
			Set<Long> peopleIds = new HashSet<Long>(replies.size());
			Set<Long> groupIds = null;
			for (WeiboReply reply : replies) {
				peopleIds.add(reply.getSenderId());
				if (reply.isFromGroup()) {
					if (groupIds == null) {
						groupIds = new HashSet<Long>(replies.size());// lazy init
					}
					groupIds.add(reply.getFromId());
				}
			}
			if (CollectionKit.isNotEmpty(peopleIds)) {
				Map<Long, String> peopleIdRealNameMap = peopleDao.getIdRealNameMapByIds(peopleIds);
				for (WeiboReply reply : replies) {
					reply.setSenderRealName(peopleIdRealNameMap.get(reply.getSenderId()));
				}
			}
			if (CollectionKit.isNotEmpty(groupIds)) {
				Map<Long, String> groupIdNameMap = groupDao.getGroupIdNameMapByIds(groupIds);
				for (WeiboReply reply : replies) {
					if (reply.isFromGroup()) {
						reply.setFromPostfix(groupIdNameMap.get(reply.getFromId()));
					}
				}
			}
		}
		return replies;
	}

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param groupDao
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public static List<Weibo> getPageWeiboWithSenderRealNameAndFrom(PeopleDao peopleDao, WeiboDao weiboDao, GroupDao groupDao, WeiboPage page) throws SQLException {
		List<Weibo> weibos = weiboDao.getPageWeibo(page);
		return injectWeibosWithSenderRealNameAndFrom(peopleDao, groupDao, weibos);
	}

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public static List<Weibo> getPageGroupWeiboWithSenderRealName(PeopleDao peopleDao, WeiboDao weiboDao, GroupDao groupDao, WeiboPage page) throws SQLException {
		List<Weibo> weibos = weiboDao.getPageGroupWeibo(page);
		return injectWeibosWithSenderRealNameAndFrom(peopleDao, groupDao, weibos);
	}
	

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param groupDao
	 * @param page
	 * @param loginPeopleId
	 * @throws SQLException 
	 */
	public static List<Weibo> getFollowingWeibosWithSenderRealNameAndFrom(PeopleDao peopleDao, WeiboDao weiboDao, GroupDao groupDao, WeiboPage page, long loginPeopleId) throws SQLException {
		List<Long> senderIds = peopleDao.getAllFollowingId(loginPeopleId);
		if(CollectionKit.isEmpty(senderIds)) {
			return null;
		}
		page.setSenderIds(senderIds);
		List<Weibo> weibos = weiboDao.getPageFollowingWeibos(page);
		return injectWeibosWithSenderRealNameAndFrom(peopleDao, groupDao, weibos);
	}

	/**
	 * @param peopleDao
	 * @param groupDao
	 * @param weibos
	 * @return
	 * @throws SQLException
	 */
	public static List<Weibo> injectWeibosWithSenderRealNameAndFrom(PeopleDao peopleDao, GroupDao groupDao, List<Weibo> weibos) throws SQLException {
		if (CollectionKit.isNotEmpty(weibos)) {
			Set<Long> peopleIds = new HashSet<Long>(weibos.size());
			Set<Long> groupIds = null;
			for (Weibo weibo : weibos) {
				peopleIds.add(weibo.getSenderId());
				if (weibo.isFromGroup()) {
					if (groupIds == null) {
						groupIds = new HashSet<Long>(weibos.size());// lazy init
					}
					groupIds.add(weibo.getFromId());
				}
			}
			if (CollectionKit.isNotEmpty(peopleIds)) {
				Map<Long, String> peopleIdRealNameMap = peopleDao.getIdRealNameMapByIds(peopleIds);
				for (Weibo weibo : weibos) {
					weibo.setSenderRealName(peopleIdRealNameMap.get(weibo.getSenderId()));
				}
			}
			if (CollectionKit.isNotEmpty(groupIds)) {
				Map<Long, String> groupIdNameMap = groupDao.getGroupIdNameMapByIds(groupIds);
				for (Weibo weibo : weibos) {
					if (weibo.isFromGroup()) {
						weibo.setFromPostfix(groupIdNameMap.get(weibo.getFromId()));
					}
				}
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
			Map<Long, String> map = peopleDao.getIdRealNameMapByIds(peopleIds);
			for (Message message : messages) {
				message.setSenderRealName(map.get(message.getSenderId()));
				message.setReceiverRealName(map.get(message.getReceiverId()));
			}
		}
		return messages;
	}

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param groupDao
	 * @param weiboId
	 * @return
	 * @throws SQLException
	 */
	public static Weibo getWeiboWithSenderRealNameAndFrom(PeopleDao peopleDao, WeiboDao weiboDao, GroupDao groupDao, long weiboId) throws SQLException {
		Weibo weibo = weiboDao.getWeiboById(weiboId);
		People people = peopleDao.getPeopleById(weibo.getSenderId());
		weibo.setSenderRealName(people.getRealName());
		if (weibo.isFromGroup()) {
			Group group = groupDao.getGroupById(weibo.getFromId());
			weibo.setFromPostfix(group.getName());
		}
		return weibo;
	}

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param groupDao
	 * @param replyId
	 * @return
	 * @throws SQLException
	 */
	public static WeiboReply getWeiboReplyWithSenderRealNameAndFrom(PeopleDao peopleDao, WeiboDao weiboDao, GroupDao groupDao, long replyId) throws SQLException {
		WeiboReply reply = weiboDao.getWeiboReplyById(replyId);
		People people = peopleDao.getPeopleById(reply.getSenderId());
		reply.setSenderRealName(people.getRealName());
		if (reply.isFromGroup()) {
			Group group = groupDao.getGroupById(reply.getFromId());
			reply.setFromPostfix(group.getName());
		}
		return reply;
	}

}
