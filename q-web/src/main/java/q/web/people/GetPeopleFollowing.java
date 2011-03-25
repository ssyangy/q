/**
 * 
 */
package q.web.people;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.dao.page.PeopleRelationPage;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 23, 2011
 *
 */
public class GetPeopleFollowing extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long fromPeopleId = context.getResourceIdLong();
		PeopleRelationPage page = new PeopleRelationPage();
		page.setFromPeopleId(fromPeopleId);
		page.setStatus(PeopleRelationStatus.FOLLOWING);
		page.setSize(20);
		page.setStartIndex(0);
		List<PeopleRelation> relations = this.peopleDao.getPeopleRelationsByPage(page);
		DaoHelper.injectPeopleRelationsWithToRealName(peopleDao, relations);
		context.setModel("relations", relations);
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
