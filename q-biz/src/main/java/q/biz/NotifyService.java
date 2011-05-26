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
	 * @param reply
	 */
	void notifyWeiboReply(WeiboReply reply);

	/**
	 * @param weibo
	 */
	void notifyWeibo(Weibo weibo);
	
	/**
	 * @param reply
	 */
	void notifyMessageReply(MessageReply reply, Collection<Long> receiverIds);

	/**
	 * @param groupId
	 * @param weibo
	 */
	void notifyGroupWeibo(long groupId, Weibo weibo);

	/**
	 * @param relation
	 */
	void notifyPeopleFollowing(PeopleRelation relation);

}
