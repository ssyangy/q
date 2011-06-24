/**
 * 
 */
package q.web.weibo;

import q.biz.WeiboService;
import q.domain.Weibo;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.WeiboNotExistException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class GetWeibo extends Resource {
	private WeiboService weiboService;

	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
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

		Weibo weibo = weiboService.getWeibo(weiboId, loginPeopleId);
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
			throw new WeiboNotExistException();
		}
	}

}
