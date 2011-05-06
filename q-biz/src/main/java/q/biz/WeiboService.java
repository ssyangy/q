/**
 * 
 */
package q.biz;

import q.domain.Weibo;
import q.domain.WeiboReply;

/**
 * @author seanlinwang at gmail dot com
 * @date May 5, 2011
 * 
 */
public interface WeiboService {

	/**
	 * @param reply
	 * @throws Exception
	 */
	void addWeiboReply(WeiboReply reply) throws Exception;

	/**
	 * @param weibo
	 * @param groupId
	 * @throws Exception
	 */
	void addWeibo(Weibo weibo, long groupId) throws Exception;

	/**
	 * @param retweet
	 * @param groupId
	 * @throws Exception
	 */
	void addWeiboRetweet(Weibo retweet, long groupId) throws Exception;

	/**
	 * @param retweet
	 * @param groupId
	 * @throws Exception
	 */
	void addReplyRetweet(Weibo retweet, long groupId) throws Exception;

}
