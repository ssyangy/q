/**
 * 
 */
package q.biz;

import java.util.Collection;

import q.domain.MessageReply;
import q.domain.PeopleRelation;
import q.domain.Weibo;
import q.domain.WeiboReply;

/**
 * @author seanlinwang at gmail dot com
 * @date May 5, 2011
 * 
 */
public interface NotifyService {
	/**
	 * @param weibo
	 */
	void notifyWeibo(Weibo weibo);

	/**
	 * @param groupId
	 * @param weibo
	 */
	void notifyGroupWeibo(long groupId, Weibo weibo);

	/**
	 * @param reply
	 */
	void notifyWeiboReply(WeiboReply reply);

	/**
	 * @param reply
	 * @param receiverIds
	 * @param loginId 
	 */
	void notifyMessageReply(MessageReply reply, Collection<Long> receiverIds, long loginId);

	/**
	 * @param relation
	 */
	void notifyPeopleFollowing(PeopleRelation relation);

	/**
	 * @param fromPeopleId
	 * @param toPeopleId
	 */
	void notifyAt(long fromPeopleId, long toPeopleId);

}
