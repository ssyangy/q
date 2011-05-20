package q.web.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.WeiboModel;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;

public class GetSearchWeibo extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		if (context.isApiRequest()) {
			String search = context.getString("search");
			List<? extends WeiboModel> weibos = null;
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId");
			int type = context.getInt("type", 0);
			int asc = 1;
			if (startId != 0) {
				search = search + " " + "AND id:[* TO " + startId + "]";
			}
			boolean hasPrev = false;
			boolean hasNext = false;
			Map<String, Object> api = new HashMap<String, Object>();
			if (StringKit.isNotEmpty(search)) {
				List<Long> bs = searchService.searchWeibo(search, size);
				if (bs != null) {
					if (bs.size() > 0) {
						if (type == asc) { // this action from next page
							hasNext = true;
						} else if (startId != IdCreator.MAX_ID) {// this action from previous page
							hasPrev = true;
						}
						weibos = weiboDao.getWeibosByIds(bs, true);
						if (CollectionKit.isNotEmpty(weibos)) {
							DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
							DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
							api.put("weibos", weibos);
						}
					}
				}
			}
			api.put("hasPrev", hasPrev);
			api.put("hasNext", hasNext);
			context.setModel("api", api);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {

	}

}
