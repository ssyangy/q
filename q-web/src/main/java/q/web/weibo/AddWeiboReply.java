/**
 * 
 */
package q.web.weibo;

import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddWeiboReply extends Resource {
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

		long quoteId = context.getResourceIdLong();
		Weibo quote = weiboDao.getWeiboById(quoteId);
		if (quote == null) {
			throw new IllegalStateException();
		}

		WeiboReply reply = new WeiboReply();
		reply.setQuoteWeiboId(quote.getId());
		reply.setQuoteSenderId(quote.getSenderId());
		long replyWeiboId = context.getLongId("replyId");
		if (replyWeiboId > 0) { // exsits replied comment
			WeiboReply replied = weiboDao.getWeiboReplyById(replyWeiboId);
			reply.setReplyWeiboId(replied.getId());
			reply.setReplySenderId(replied.getReplySenderId());
		}
		reply.setSenderId(senderId);
		reply.setContent(content);
		this.weiboDao.addWeiboReply(reply);
		context.redirectServletPath("/weibo/" + quoteId);
	}
}
