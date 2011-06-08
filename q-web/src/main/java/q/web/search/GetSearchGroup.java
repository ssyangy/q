package q.web.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.biz.SearchService;
import q.dao.GroupDao;
import q.domain.Group;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;

public class GetSearchGroup extends Resource {
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
			List<Group> groups = null;
			int size = context.getInt("size", 1);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			int fetchSize = size + 1;
			int asc = 1;
			boolean hasPrev = false;
			boolean hasNext = false;
			String searchAft = "";
			if(StringKit.isEmpty(search)) {
				if(type != 1) {
					search = "*:* AND id:[* TO " + (startId - 1) + "]";
				} else {
					search = "*:* AND id:[" + (startId) + " TO *]";
				}

			} else if (StringKit.isNotEmpty(search) && type != 1) {
				searchAft = " " + "AND id:[* TO " + (startId - 1) + "]";
			} else if(StringKit.isNotEmpty(search) && type == 1) {
				searchAft = " " + "AND id:[" + (startId) + " TO *]";
			}
			search = search + searchAft;
			Map<String, Object> api = new HashMap<String, Object>();
			if (StringKit.isNotEmpty(search)) {
				List<Long> bs = searchService.searchGroup(search, type, fetchSize);
				if(bs.size() == fetchSize) {
					if(type != 1) {
						bs.remove(bs.size() - 1);
					} else {
						bs.remove(0);
					}
					if (type == asc && bs.get(bs.size() - 1) != IdCreator.MAX_ID) { // more than one previous page
						hasPrev = true;
					} else { // more than one next page
						hasNext = true;
					}
				}
				groups = this.groupDao.getGroupsByIds(bs);
				if (CollectionKit.isNotEmpty(groups)) {
					Collections.sort(groups, idDescComparator); // sort groups order by id desc
					if (type == asc) { // this action came from next page
						hasNext = true;
					} else if (startId != IdCreator.MAX_ID && startId != 0) {// this action came from previous page
						hasPrev = true;
					}
					api.put("groups", groups);
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
