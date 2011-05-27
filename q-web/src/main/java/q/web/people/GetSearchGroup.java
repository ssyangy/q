package q.web.people;

import java.util.List;

import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.domain.Group;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

public class GetSearchGroup extends Resource{
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
		String search=context.getString("search");
		List<Group> groups = null;
		if(search!=null&search!=""){
	        List<Long>bs=searchService.searchGroup(search);
	        if(bs!=null){
	        if(bs.size()>0){
	        	groups =  this.groupDao.getGroupsByIds(bs);

	           context.setModel("groups", groups);

	        }
	        }
		}

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
