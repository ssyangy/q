package q.web.search;

import java.util.Map;

import q.biz.WeiboService;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;

public class GetSearchWeibo extends Resource {
	private WeiboService weiboService;

	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (context.isApiRequest()) {
			String search = context.getString("search");
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			Map<String, Object> api = weiboService.getSearchWeiboPagination(search, loginPeopleId, size, startId, type);
			context.setModel("api", api);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {

	}

}
