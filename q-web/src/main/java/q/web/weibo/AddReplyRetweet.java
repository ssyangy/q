/**
 *
 */
package q.web.weibo;

import q.biz.WeiboService;
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

		this.weiboService.addReplyRetweet(retweet, -1);

		String from = context.getString("from");
		if (from != null) {
			context.redirectContextPath(from);
		}
	}

	@Override
	public void validate(ResourceContext context) {
	}

}
