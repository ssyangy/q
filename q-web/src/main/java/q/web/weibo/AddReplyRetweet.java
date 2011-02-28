/**
 * 
 */
package q.web.weibo;

import java.sql.SQLException;

import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.domain.WeiboJoinGroup;
import q.domain.WeiboReply;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 * 
 */
public class AddReplyRetweet extends Resource {
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

		WeiboReply father = weiboDao.getWeiboReplyById(context.getResourceIdLong());
		if (father == null) {
			throw new IllegalStateException();
		}

		Weibo retweet = new Weibo();
		retweet.setSenderId(senderId);
		retweet.setQuoteWeiboId(father.getQuoteWeiboId());
		retweet.setQuoteSenderId(father.getQuoteSenderId());
		retweet.setReplyWeiboId(father.getId());
		retweet.setReplySenderId(father.getSenderId());
		retweet.setContent(content);
		WeiboJoinGroup join = weiboDao.getWeiboJoinGroupByWeiboId(father.getQuoteWeiboId());
		if (join != null && join.isValid()) {
			retweet.setFromType(WeiboFromType.GROUP);
			retweet.setFromId(join.getGroupId());
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
