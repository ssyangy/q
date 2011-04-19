package q.web.people;

import java.util.List;

import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.WeiboModel;
import q.web.Resource;
import q.web.ResourceContext;

public class GetSearchIndex extends Resource {
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
		String search = context.getString("search");
		List<? extends WeiboModel> weibos = null;
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		if (startId != 0) {
			search = search + " " + "AND id:[* TO " + startId + "]";
		}
		log.debug("search query: %s", search);
		if (search != null & search != "") {
			List<Long> bs = searchService.searchWeibo(search, size);
			if (bs != null) {
				if (bs.size() > 0) {
					weibos = weiboDao.getWeibosByIds(bs, true);
					DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
					DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
					context.setModel("weibos", weibos);

				}
			}
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {

	}

}
