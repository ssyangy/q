/**
 * 
 */
package q.web.weibo;

import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Weibo;
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

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	
	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long senderId = context.getLoginPeopleId();
		long quoteWeiboId = context.getResourceIdLong();
		String content = context.getString("content");
		String from = context.getString("from");

		Weibo quote = weiboDao.getWeiboById(quoteWeiboId);
		if(quote == null) {
			throw new IllegalStateException();
		}
		
		Weibo weibo = new Weibo();
		weibo.setSenderId(senderId);
		weibo.setContent(content);
		weibo.setQuoteWeiboId(quoteWeiboId);
		weibo.setQuoteSenderId(quote.getQuoteSenderId());
		this.weiboDao.addWeibo(weibo);
		if (from != null) {
			context.redirectContextPath(from);
		} else {
			context.redirectServletPath("/weibo/" + weibo.getId());
		}
	}

}
