/**
 *
 */
package q.web.weibo;

import q.biz.WeiboService;
import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.domain.WeiboJoinGroup;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

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

	private WeiboService weiboService;

	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
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
		String from = context.getString("from");

		Weibo father = weiboDao.getWeiboById(context.getResourceIdLong());
		if (father == null) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}

		Weibo retweet = new Weibo();
		retweet.setSenderId(senderId);
		retweet.setQuoteWeiboId(father.getQuoteWeiboId() == 0 ? father.getId() : father.getQuoteWeiboId()); // if replied weibo is origin, use replied id as quote id.
		retweet.setQuoteSenderId(father.getQuoteSenderId() == 0 ? father.getSenderId() : father.getQuoteSenderId());
		retweet.setReplyWeiboId(father.getId());
		retweet.setReplySenderId(father.getSenderId());
		retweet.setContent(content);
		WeiboJoinGroup join = weiboDao.getWeiboJoinGroupByWeiboId(father.getId());
		if (join != null && join.isValid()) {
			retweet.setFromType(WeiboFromType.GROUP);
			retweet.setFromId(join.getGroupId());
		}

		this.weiboService.addWeiboRetweet(retweet, -1);

		if (from != null) {
			context.redirectContextPath(from);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long quoteId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(quoteId)) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}
	}

}
