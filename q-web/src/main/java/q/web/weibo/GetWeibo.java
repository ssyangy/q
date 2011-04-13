/**
 * 
 */
package q.web.weibo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import q.dao.DaoHelper;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboReplyPage;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.util.CollectionKit;
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

		WeiboReplyPage page = new WeiboReplyPage();
		page.setQuoteWeiboId(weiboId);
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		int type = context.getInt("type", 0);
		int asc = 1;
		if (type == asc) { // 1 indicate asc
			page.setDesc(false);
		} else {
			page.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		page.setSize(fetchSize);
		page.setStartIndex(0);
		if (startId > 0) {
			page.setStartId(startId);
		}
		List<WeiboReply> replies = weiboDao.getWeiboRepliesByPage(page);
		if (CollectionKit.isNotEmpty(replies)) {
			if (replies.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				replies.remove(replies.size() - 1);//remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if(startId != 999999999999999999L){// this action from previous page
				hasPrev = true;
			}
			if (type == asc) { // reverse asc to desc
				WeiboReply[] array = replies.toArray(new WeiboReply[replies.size()]);
				CollectionUtils.reverseArray(array);
				replies = Arrays.asList(array);
			}
			DaoHelper.injectWeiboModelsWithPeople(peopleDao, replies);
			DaoHelper.injectWeiboModelsWithFrom(groupDao, replies);
			if (loginPeopleId > 0) {
				DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, replies, loginPeopleId);
			}
		}
		

		Map<String, Object> api = new HashMap<String, Object>();
		api.put("weibo", weibo);
		if (CollectionKit.isNotEmpty(replies)) {
			api.put("replies", replies);
		}
		
		api.put("hasPrev", hasPrev);
		api.put("hasNext", hasNext);
		context.setModel("api", api);
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
