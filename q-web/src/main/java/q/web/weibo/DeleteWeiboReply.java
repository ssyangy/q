/**
 *
 */
package q.web.weibo;

import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang at gmail dot com
 * @date Apr 20, 2011
 * 
 */
public class DeleteWeiboReply extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginId = context.getCookiePeopleId();
		long weiboId = context.getResourceIdLong();
		long replyWeiboId = context.getIdLong("replyId");

		Weibo weibo = this.weiboDao.getWeiboById(weiboId);
		if (weibo == null) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}
		WeiboReply reply = this.weiboDao.getWeiboReplyById(replyWeiboId);
		if (reply == null || reply.getQuoteWeiboId() != weibo.getId() || reply.getSenderId() != loginId) {
			throw new RequestParameterInvalidException("reply:invalid");
		}

		if (reply.getStatus() == Status.COMMON.getValue()) {
			int rowEffected = this.weiboDao.deleteWeiboReplyBySenderIdAndReplyId(loginId, reply.getId());
			if (rowEffected > 0) {
				this.weiboDao.decrWeiboReplyNumByWeiboId(reply.getQuoteWeiboId()); //decr reply number
			}
		}

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
		long loginId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginId)) {
			throw new RequestParameterInvalidException("login:invalid");
		}
		long weiboId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(weiboId)) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}
		long replyWeiboId = context.getIdLong("replyId");
		if (IdCreator.isNotValidId(replyWeiboId)) {
			throw new RequestParameterInvalidException("reply:invalid");
		}
	}
}
