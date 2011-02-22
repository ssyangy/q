/**
 * 
 */
package q.web.weibo;

import q.dao.WeiboDao;
import q.domain.Weibo;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddWeibo extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getLoginPeopleId();
		long groupId = context.getLong("groupId");
		String content = context.getString("content");
		String from = context.getString("from");
		
		Weibo weibo = new Weibo();
		weibo.setSenderId(peopleId);
		weibo.setContent(content);
		this.weiboDao.addWeibo(weibo);
		this.weiboDao.addWeiboJoinGroup(weibo.getId(), groupId);
		if (from != null) {
			context.redirectContextPath(from);
		} else {
			context.emptyView();
		}

	}
}
