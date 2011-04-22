/**
 *
 */
package q.web.weibo;

import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang at gmail dot com
 * @date Apr 20, 2011
 * 
 */
public class DeleteWeibo extends Resource {
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
		long senderId = context.getCookiePeopleId();
		long weiboId = context.getResourceIdLong();

		Weibo weibo = this.weiboDao.getWeiboById(weiboId);
		if (weibo.getStatus() == Status.COMMON.getValue()) {
			int rowEffected = this.weiboDao.deleteWeiboBySenderIdAndWeiboId(senderId, weiboId);
			if (rowEffected > 0) { // decr weibo number if delete weibo successful.
				this.peopleDao.decrPeopleWeiboNumberByPeopleId(senderId);
				if(weibo.getQuoteWeiboId() > 0) {
					this.weiboDao.decrWeiboRetweetNumByWeiboId(weibo.getQuoteWeiboId());
				}
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
		long senderId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(senderId)) {
			throw new RequestParameterInvalidException("login:invalid");
		}
		long weiboId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(weiboId)) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}
		Weibo weibo = this.weiboDao.getWeiboById(weiboId);
		if (weibo.getSenderId() != senderId) {
			throw new RequestParameterInvalidException("login:invalid");
		}
	}
}
