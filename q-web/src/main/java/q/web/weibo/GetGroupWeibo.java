/**
 *
 */
package q.web.weibo;

import java.util.Map;

import q.biz.WeiboService;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.GroupNotExistException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 16, 2011
 * 
 */
public class GetGroupWeibo extends Resource {
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
		long groupId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId", IdCreator.MAX_ID);
		int type = context.getInt("type", 0);
		Map<String, Object> api = weiboService.getGroupWeiboPagination(groupId, loginPeopleId, size, startId, type);
		context.setModel("api", api);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long groupId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(groupId)) {
			throw new GroupNotExistException();
		}
	}

}
