/**
 * 
 */
package q.web.people;

import java.util.List;

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
public class GetPeopleFollower extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long toPeopleId = context.getResourceIdLong();
		PeopleRelationPage page = new PeopleRelationPage();
		page.setToPeopleId(toPeopleId);
		page.setStatus(PeopleRelationStatus.FOLLOWING);
		page.setSize(20);
		List<PeopleRelation> relations = this.peopleDao.getPagePeopleRelationWithFromRealName(page);
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
