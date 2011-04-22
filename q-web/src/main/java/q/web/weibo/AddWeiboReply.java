/**
 * 
 */
package q.web.weibo;

import q.biz.ShortUrlService;
import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.domain.WeiboJoinGroup;
import q.domain.WeiboReply;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

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

	private ShortUrlService shortUrlService;

	public void setShortUrlService(ShortUrlService shortUrlService) {
		this.shortUrlService = shortUrlService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		String content = context.getString("content");

		long quoteId = context.getResourceIdLong();
		Weibo quote = weiboDao.getWeiboById(quoteId);
		if (quote == null) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}

		WeiboReply reply = new WeiboReply();
		reply.setQuoteWeiboId(quote.getId());
		reply.setQuoteSenderId(quote.getSenderId());
		long replyWeiboId = context.getIdLong("replyId");
		if (replyWeiboId > 0) { // exsits replied comment
			WeiboReply replied = weiboDao.getWeiboReplyById(replyWeiboId);
			reply.setReplyWeiboId(replied.getId());
			reply.setReplySenderId(replied.getReplySenderId());
		}
		reply.setSenderId(senderId);
		content = this.shortUrlService.urlFilter(content);
		reply.setContent(content);
		WeiboJoinGroup join = weiboDao.getWeiboJoinGroupByWeiboId(quote.getId());
		if (join != null && join.isValid()) {
			reply.setFromType(WeiboFromType.GROUP);
			reply.setFromId(join.getGroupId());
		}
		
		this.weiboDao.addWeiboReply(reply);
		this.weiboDao.incrWeiboReplyNumByReplyId(reply.getQuoteWeiboId());
		
		String from = context.getString("from");
		if (from != null) {
			context.redirectContextPath(from);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long quoteId = context.getResourceIdLong();
		if(IdCreator.isNotValidId(quoteId)) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}

	}
}
