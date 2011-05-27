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
public interface NotifyService {

	/**
	 * @param reply
	 */
	void notifyWeiboReply(WeiboReply reply);

	/**
	 * @param weibo
	 */
	void notifyWeibo(Weibo weibo);

}
