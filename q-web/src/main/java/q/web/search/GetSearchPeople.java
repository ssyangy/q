package q.web.search;

import java.util.Collections;
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
	private static long maxId;
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
			int fetchSize = size + 1;
			int asc = 1;
			boolean hasPrev = false;
			boolean hasNext = false;
			String searchAft = "";
			if(startId == 0) {
				startId = IdCreator.MAX_ID;
			}
			if(StringKit.isEmpty(search)) {
				if(type != 1) {
					searchAft = "*:* AND id:[* TO " + (startId - 1) + "]";
				} else {
					searchAft = "*:* AND id:[" + (startId) + " TO *]";
				}

			} else if (StringKit.isNotEmpty(search) && type != 1) {
				searchAft = " " + "AND id:[* TO " + (startId - 1) + "]";
			} else if(StringKit.isNotEmpty(search) && type == 1) {
				searchAft = " " + "AND id:[" + (startId) + " TO *]";
			}
			search = search + searchAft;
			Map<String, Object> api = new HashMap<String, Object>();
			if (StringKit.isNotEmpty(search)) {
				List<Long> bs = searchService.searchPeople(search, type, fetchSize);
				if(startId == IdCreator.MAX_ID) {
					maxId = bs.get(0);
				}
				if(bs.size() == fetchSize) {
					if(type != 1) {
						bs.remove(bs.size() - 1);
					} else {
						bs.remove(0);
					}
					if (type == asc && bs.get(bs.size() - 1) != maxId) { // more than one previous page
						hasPrev = true;
					} else { // more than one next page
						hasNext = true;
					}
				}
				peoples = this.peopleDao.getPeoplesByIds(bs);
				if (CollectionKit.isNotEmpty(peoples)) {
					Collections.sort(peoples, idDescComparator); // sort groups order by id desc
					if (type == asc) { // this action came from next page
						hasNext = true;
					} else if (startId != IdCreator.MAX_ID && startId != 0) {// this action came from previous page
						hasPrev = true;
					}
					if (loginPeopleId > 0) {
						DaoHelper.injectPeoplesWithVisitorRelation(peopleDao, peoples, loginPeopleId);
					}
					api.put("peoples", peoples);
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
