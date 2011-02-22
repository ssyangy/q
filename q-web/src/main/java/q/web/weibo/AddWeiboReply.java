/**
 * 
 */
package q.web.weibo;

import q.dao.WeiboDao;
import q.domain.WeiboReply;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddWeiboReply extends Resource {
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
		long weiboId = context.getResourceIdLong();
		String content = context.getString("content");
		
		WeiboReply reply = new WeiboReply();
		reply.setQuoteWeiboId(weiboId);
		reply.setSenderId(peopleId);
		reply.setContent(content);
		this.weiboDao.addWeiboReply(reply);
		context.redirectServletPath("/weibo/" + weiboId);
	}
}
