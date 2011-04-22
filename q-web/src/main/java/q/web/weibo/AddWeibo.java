/**
 *
 */
package q.web.weibo;

import q.biz.PictureService;
import q.biz.SearchService;
import q.biz.ShortUrlService;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @author bububut
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddWeibo extends Resource {
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

	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
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
		Weibo weibo = new Weibo();
		weibo.setStatus(Status.COMMON.getValue());
		long senderId = context.getCookiePeopleId();
		weibo.setSenderId(senderId);
		String content = context.getString("content");
		content = this.shortUrlService.urlFilter(content);
		weibo.setContent(content);
		String picturePath = context.getString("picPath");
		if (StringKit.isNotEmpty(picturePath)) {
			weibo.setPicturePath(picturePath);
		}
		long groupId = context.getIdLong("groupId");
		if (IdCreator.isValidIds(groupId)) {
			weibo.setFromType(WeiboFromType.GROUP);
			weibo.setFromId(groupId);
		}
		
		this.weiboDao.addWeibo(weibo);
		this.peopleDao.incrPeopleWeiboNumberByPeopleId(senderId);

		if (IdCreator.isValidIds(groupId)) {
			this.weiboDao.addWeiboJoinGroup(weibo.getId(), weibo.getSenderId(), groupId);
		}

		//FIXME will remove here, sean
		searchService.updateWeibo(weibo);

		String upimgfix = context.getString("upimgfix");
		if (StringKit.isNotEmpty(upimgfix)) {
			int fix = Integer.parseInt(upimgfix);
			pictureService.rotate(picturePath, fix);
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
		if (senderId <= 0) {
			throw new RequestParameterInvalidException("login:invalid");
		}
		String content = context.getString("content");
		if (null == content) {
			throw new RequestParameterInvalidException("content:invalid");
		}

	}
}
