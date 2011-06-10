package q.web.weibo;

import java.util.Map;

import q.biz.WeiboService;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotExistException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Jan 18, 2011
 * 
 */
public class GetPeopleWeibo extends Resource {

	private WeiboService weiboService;

	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId", IdCreator.MAX_ID);
		int type = context.getInt("type", 0);
		Map<String, Object> api = weiboService.getPeopleWeiboPagination(peopleId, loginPeopleId, size, startId, type);
		context.setModel("api", api);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long peopleId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(peopleId)) {
			throw new PeopleNotExistException();
		}
	}
}
