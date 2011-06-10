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
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.RequestParameterInvalidException;

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
			throw new RequestParameterInvalidException("reply:invalid");
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

		this.weiboService.addReplyRetweet(retweet);
	}

	@Override
	public void validate(ResourceContext context) throws RequestParameterInvalidException, PeopleNotLoginException {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long replyId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(replyId)) {
			throw new RequestParameterInvalidException("reply:invalid");
		}
		String content = context.getString("content");
		if (StringKit.isBlank(content)) {
			throw new RequestParameterInvalidException("content:invalid");
		}
		if (content.length() > 1400) {
			throw new RequestParameterInvalidException("content:overflow");
		}
	}

}
