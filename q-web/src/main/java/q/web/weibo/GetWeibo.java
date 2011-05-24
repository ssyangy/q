/**
 * 
 */
package q.web.weibo;

import q.dao.DaoHelper;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Weibo;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class GetWeibo extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long weiboId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();

		Weibo weibo = weiboDao.getWeiboById(weiboId);
		if (weibo.getQuoteWeiboId() > 0) {
			DaoHelper.injectWeiboModelWithQuote(weiboDao, weibo);
		}
		DaoHelper.injectWeiboModelWithPeople(peopleDao, weibo);
		DaoHelper.injectWeiboModelWithFrom(groupDao, weibo);
		if (loginPeopleId > 0) {
			DaoHelper.injectWeiboWithFavorite(favoriteDao, weibo, loginPeopleId);
		}
		context.setModel("weibo", weibo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long weiboId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(weiboId)) {
			throw new RequestParameterInvalidException("weibo:invalid");
		}
	}

}
