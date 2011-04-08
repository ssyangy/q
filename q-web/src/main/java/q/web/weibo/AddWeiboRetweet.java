/**
 *
 */
package q.web.weibo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import q.biz.SearchService;
import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.domain.WeiboJoinGroup;
import q.http.JdkHttpClient;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;

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
		String content = context.getString("content");
		String from = context.getString("from");

		Weibo father = weiboDao.getWeiboById(context.getResourceIdLong());
		if (father == null) {
			throw new IllegalStateException();
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
		this.weiboDao.addWeibo(retweet);
		searchService.updateWeibo(retweet);
		if (from != null) {
			context.redirectContextPath(from);
		}
	}

	@Override
	public void validate(ResourceContext context) throws SQLException {
	}

}
