/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.domain.Favorite;
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
	public static void injectWeiboRepliesWithSenderRealNameAndFrom(PeopleDao peopleDao, GroupDao groupDao, List<WeiboReply> replies) throws SQLException {
		if (CollectionKit.isNotEmpty(replies)) {
			Set<Long> senderIds = new HashSet<Long>(replies.size());
			Set<Long> groupIds = null;
			for (WeiboReply reply : replies) {
				senderIds.add(reply.getSenderId());
				if (reply.isFromGroup()) {
					if (groupIds == null) {
						groupIds = new HashSet<Long>(replies.size());// lazy init
					}
					groupIds.add(reply.getFromId());
				}
			}
			if (CollectionKit.isNotEmpty(senderIds)) {
				Map<Long, String> peopleIdRealNameMap = peopleDao.getIdRealNameMapByIds(senderIds);
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
	}

	public static void injectWeiboRepliesWithFavorite(FavoriteDao favoriteDao, List<WeiboReply> replies, long peopleId) throws SQLException {
		if (CollectionKit.isNotEmpty(replies)) {
			List<Long> replyIds = new ArrayList<Long>(replies.size());
			for (WeiboReply reply : replies) {
				replyIds.add(reply.getId());
			}
			List<Long> favIds = favoriteDao.getFavReplyIds(replyIds, peopleId);
			if (CollectionKit.isNotEmpty(favIds)) {
				Set<Long> favSet = new HashSet<Long>(favIds);
				for (WeiboReply reply : replies) {
					if (favSet.contains(reply.getId())) {
						reply.setFav(true);
					}
				}
			}
		}
	}

	/**
	 * @param peopleDao
	 * @param groupDao
	 * @param weibos
	 * @return
	 * @throws SQLException
	 */
	public static void injectWeibosWithSenderRealNameAndFrom(PeopleDao peopleDao, GroupDao groupDao, List<Weibo> weibos) throws SQLException {
		if (CollectionKit.isNotEmpty(weibos)) {
			Set<Long> senderIds = new HashSet<Long>(weibos.size());
			Set<Long> groupIds = null;
			for (Weibo weibo : weibos) {
				senderIds.add(weibo.getSenderId());
				if (weibo.isFromGroup()) {
					if (groupIds == null) {
						groupIds = new HashSet<Long>(weibos.size());// lazy init
					}
					groupIds.add(weibo.getFromId());
				}
			}
			if (CollectionKit.isNotEmpty(senderIds)) {
				Map<Long, String> peopleIdRealNameMap = peopleDao.getIdRealNameMapByIds(senderIds);
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
	}

	public static void injectWeibosWithFavorite(FavoriteDao favoriteDao, List<Weibo> weibos, long peopleId) throws SQLException {
		if (CollectionKit.isNotEmpty(weibos)) {
			List<Long> replyIds = new ArrayList<Long>(weibos.size());
			for (Weibo weibo : weibos) {
				replyIds.add(weibo.getId());
			}
			List<Long> favIds = favoriteDao.getFavWeiboIds(replyIds, peopleId);
			if (CollectionKit.isNotEmpty(favIds)) {
				Set<Long> favSet = new HashSet<Long>(favIds);
				for (Weibo weibo : weibos) {
					if (favSet.contains(weibo.getId())) {
						weibo.setFav(true);
					}
				}
			}
		}
	}

	/**
	 * @param peopleDao
	 * @param groupDao
	 * @param weiboDao
	 * @param favorites
	 */
	public static void injectFavoritesWithSource(PeopleDao peopleDao, GroupDao groupDao, WeiboDao weiboDao, List<Favorite> favorites) throws SQLException {
		if (CollectionKit.isNotEmpty(favorites)) {
			List<Long> sourceWeiboIds = null; // lazy init
			List<Long> sourceReplyIds = null; // lazy inti
			for (Favorite fav : favorites) { // extract weibo and reply sources
				if (fav.isFromWeibo()) {
					if (sourceWeiboIds == null) {
						sourceWeiboIds = new ArrayList<Long>(favorites.size());
					}
					sourceWeiboIds.add(fav.getFromId());
				}
				if (fav.isFromReply()) {
					if (sourceReplyIds == null) {
						sourceReplyIds = new ArrayList<Long>(favorites.size());
					}
					sourceReplyIds.add(fav.getFromId());
				}
			}
			Map<Long, Weibo> weiboMap = null;
			if (CollectionKit.isNotEmpty(sourceWeiboIds)) {
				weiboMap = new HashMap<Long, Weibo>(sourceWeiboIds.size());
				List<Weibo> weibos = weiboDao.getWeibosByIds(sourceWeiboIds, false);
				for (Weibo weibo : weibos) {
					if (weibo.isCommon()) {
						weiboMap.put(weibo.getId(), weibo);
					}
				}
			}
			Map<Long, WeiboReply> replyMap = null;
			if (CollectionKit.isNotEmpty(sourceReplyIds)) {
				replyMap = new HashMap<Long, WeiboReply>(sourceReplyIds.size());
				List<WeiboReply> replies = weiboDao.getWeiboRepliesByIds(sourceReplyIds, false);
				for (WeiboReply reply : replies) {
					if (reply.isCommon()) {
						replyMap.put(reply.getId(), reply);
					}
				}
			}
			for (Favorite fav : favorites) { // inject weibo and reply sources
				if (weiboMap != null && fav.isFromWeibo()) {
					fav.setSource(weiboMap.get(fav.getFromId()));
					fav.getSource().setFav(true);
				}
				if (replyMap != null && fav.isFromReply()) {
					fav.setSource(replyMap.get(fav.getFromId()));
					fav.getSource().setFav(true);
				}
			}
		}

	}

	public static void injectMessagesWithSenderReceiverRealName(PeopleDao peopleDao, List<Message> messages) throws SQLException {
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
	}

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param groupDao
	 * @param weiboId
	 * @return
	 * @throws SQLException
	 */
	public static void injectWeiboWithSenderRealNameAndFrom(PeopleDao peopleDao, GroupDao groupDao, Weibo weibo) throws SQLException {
		People people = peopleDao.getPeopleById(weibo.getSenderId());
		weibo.setSenderRealName(people.getRealName());
		if (weibo.isFromGroup()) {
			Group group = groupDao.getGroupById(weibo.getFromId());
			weibo.setFromPostfix(group.getName());
		}
	}

	/**
	 * @param peopleDao
	 * @param weiboDao
	 * @param groupDao
	 * @param replyId
	 * @return
	 * @throws SQLException
	 */
	public static void injectWeiboReplyWithSenderRealNameAndFrom(PeopleDao peopleDao, GroupDao groupDao, WeiboReply reply) throws SQLException {
		People people = peopleDao.getPeopleById(reply.getSenderId());
		reply.setSenderRealName(people.getRealName());
		if (reply.isFromGroup()) {
			Group group = groupDao.getGroupById(reply.getFromId());
			reply.setFromPostfix(group.getName());
		}
	}

}
