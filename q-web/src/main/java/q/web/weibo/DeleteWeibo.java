/**
 *
 */
package q.web.weibo;

import q.biz.SearchService;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;
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

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
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
		if (weibo.getStatus() == Status.COMMON.getValue()) { // only delete weibo which has common status
			int rowEffected = this.weiboDao.deleteWeiboById(weibo.getId());
			if (rowEffected == 1) { // if delete weibo successful.
				this.peopleDao.decrPeopleWeiboNumberByPeopleId(senderId);// decr weibo number
				if (weibo.getQuoteWeiboId() > 0) {
					this.weiboDao.decrWeiboRetweetNumberByWeiboId(weibo.getQuoteWeiboId());// decr weibo retweet number
				}
			}
		}
		// delete weibo groups
		this.weiboDao.deleteWeiboJoinGroupsByWeiboId(weiboId);
		searchService.deleteWeiboById(weiboId);
		context.setModel("weibo", weibo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long weiboId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(weiboId)) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}
		Weibo weibo = this.weiboDao.getWeiboById(weiboId);
		if (weibo.getSenderId() != loginPeopleId) {
			throw new PeopleNotPermitException();
		}
	}
}
