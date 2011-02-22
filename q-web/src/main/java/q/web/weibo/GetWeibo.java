/**
 * 
 */
package q.web.weibo;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboReplyPage;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.web.Resource;
import q.web.ResourceContext;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long weiboId = context.getResourceIdLong();
		Weibo weibo = weiboDao.getWeiboById(weiboId);
		context.setModel("weibo", weibo);
		WeiboReplyPage page = new WeiboReplyPage();
		page.setQuoteWeiboId(weiboId);
		page.setSize(20);
		page.setStartId(0);
		page.setStartIndex(0);
		List<WeiboReply> replies = DaoHelper.getPageWeiboReply(peopleDao, weiboDao, page);
		context.setModel("replies", replies);
		log.debug("weibo:%s, replies:%s", weibo, replies);
	}

}
