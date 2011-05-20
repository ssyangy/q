/**
 * 
 */
package q.biz.impl;

import java.sql.SQLException;

import q.biz.NotifyService;
import q.biz.SearchService;
import q.biz.ShortUrlService;
import q.biz.WeiboService;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.util.IdCreator;

/**
 * @author seanlinwang at gmail dot com
 * @date May 5, 2011
 * 
 */
public class DefaultWeiboService implements WeiboService {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private NotifyService notifyService;

	public void setNotifyService(NotifyService notifyService) {
		this.notifyService = notifyService;
	}

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private ShortUrlService shortUrlService;

	public void setShortUrlService(ShortUrlService shortUrlService) {
		this.shortUrlService = shortUrlService;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addWeiboReply(q.domain.WeiboReply)
	 */
	@Override
	public void addWeiboReply(WeiboReply reply) throws SQLException {
		reply.setContent(this.shortUrlService.urlFilter(reply.getContent()));
		this.weiboDao.addWeiboReply(reply);
		this.weiboDao.incrWeiboReplyNumByReplyId(reply.getQuoteWeiboId());
		this.notifyService.notifyWeiboReply(reply);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addWeibo(q.domain.Weibo)
	 */
	@Override
	public void addWeibo(Weibo weibo, long groupId) throws Exception {
		this.addWeiboCommon(weibo, groupId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addWeiboRetweet(q.domain.Weibo)
	 */
	@Override
	public void addWeiboRetweet(Weibo retweet, long groupId) throws Exception {
		this.addWeiboCommon(retweet, groupId);
		this.weiboDao.incrWeiboRetweetNumByWeiboId(retweet.getQuoteWeiboId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addReplyRetweet(q.domain.Weibo, long)
	 */
	@Override
	public void addReplyRetweet(Weibo retweet, long groupId) throws Exception {
		this.addWeiboCommon(retweet, groupId);
	}

	private void addWeiboCommon(Weibo weibo, long groupId) throws Exception {
		weibo.setContent(this.shortUrlService.urlFilter(weibo.getContent()));
		this.weiboDao.addWeibo(weibo);
		this.peopleDao.incrPeopleWeiboNumberByPeopleId(weibo.getSenderId());
		if (IdCreator.isValidIds(groupId)) {
			this.weiboDao.addWeiboJoinGroup(weibo.getId(), weibo.getSenderId(), groupId);
		}
		this.searchService.updateWeibo(weibo); // FIXME will remove here, sean
		this.notifyService.notifyWeibo(weibo);
		if (IdCreator.isValidIds(groupId)) {
			this.notifyService.notifyGroupWeibo(groupId, weibo);
		}

	}

}
