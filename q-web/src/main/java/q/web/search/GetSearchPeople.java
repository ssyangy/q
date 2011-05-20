package q.web.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.domain.People;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;

public class GetSearchPeople extends Resource {
	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		if (context.isApiRequest()) {
			long loginPeopleId = context.getCookiePeopleId();
			String search = context.getString("search");
			List<People> peoples = null;
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId");
			int type = context.getInt("type", 0);
			int asc = 1;
			boolean hasPrev = false;
			boolean hasNext = false;
			Map<String, Object> api = new HashMap<String, Object>();
			if (StringKit.isNotEmpty(search)) {
				List<Long> bs = searchService.searchPeople(search, size);
				if (bs != null) {
					if (bs.size() > 0) {
						if (type == asc) { // this action from next page
							hasNext = true;
						} else if (startId != IdCreator.MAX_ID) {// this action from previous page
							hasPrev = true;
						}
						peoples = this.peopleDao.getPeoplesByIds(bs);
						if (CollectionKit.isNotEmpty(peoples)) {
							if (loginPeopleId > 0) {
								DaoHelper.injectPeoplesWithVisitorRelation(peopleDao, peoples, loginPeopleId);
							}
							api.put("peoples", peoples);
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
