package q.web.people;

import java.util.List;

import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.domain.People;
import q.domain.WeiboModel;
import q.web.Resource;
import q.web.ResourceContext;

public class GetSearchPeople extends Resource{
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
		long loginPeopleId = context.getCookiePeopleId();
		String search=context.getString("search");
		List<People> peoples = null;
		if(search!=null&search!=""){
	        List<Long>bs=searchService.searchPeople(search);
	        if(bs!=null){
	        if(bs.size()>0){
	        peoples =  this.peopleDao.getPeoplesByIds(bs);
	        DaoHelper.injectPeoplesWithVisitorRelation(peopleDao, peoples, loginPeopleId);
	        context.setModel("peoples", peoples);

	        }
	        }
		}

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
