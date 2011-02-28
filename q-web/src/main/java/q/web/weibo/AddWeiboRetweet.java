/**
 * 
 */
package q.web.weibo;

import java.sql.SQLException;

import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.domain.WeiboJoinGroup;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 * 
 */
public class AddWeiboRetweet extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long senderId = context.getLoginPeopleId();
		String content = context.getString("content");
		String from = context.getString("from");

		Weibo origin = weiboDao.getWeiboById(context.getResourceIdLong());
		if (origin == null) {
			throw new IllegalStateException();
		}

		Weibo retweet = new Weibo();
		retweet.setSenderId(senderId);
		retweet.setQuoteWeiboId(origin.getQuoteWeiboId() == 0 ? origin.getId() : origin.getQuoteWeiboId()); // if replied weibo is origin, use replied id as quote id.
		retweet.setQuoteSenderId(origin.getQuoteSenderId() == 0 ? origin.getSenderId() : origin.getQuoteSenderId());
		retweet.setReplyWeiboId(origin.getId());
		retweet.setReplySenderId(origin.getSenderId());
		retweet.setContent(content);
		long groupId = context.getLongId("groupId");
		if (groupId > 0) {
			WeiboJoinGroup join = weiboDao.getWeiboJoinGroupByWeiboIdAndGroupId(origin.getId(), groupId);
			if (join != null && join.isValid()) {
				retweet.setFromType(WeiboFromType.GROUP);
				retweet.setFromId(join.getGroupId());
			}
		}
		this.weiboDao.addWeibo(retweet);
		if (from != null) {
			context.redirectContextPath(from);
		} else {
			context.redirectServletPath("/weibo/" + retweet.getId());
		}
	}

	@Override
	public boolean validate(ResourceContext context) throws SQLException {
		return true;
	}

}
