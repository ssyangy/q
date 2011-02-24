/**
 * 
 */
package q.web.people;

import q.dao.PeopleDao;
import q.domain.PeopleRelationStatus;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 23, 2011
 *
 */
public class DeletePeopleFollowing extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	
	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long fromPeopleId = context.getLoginPeopleId();
		long toPeopleId = context.getResourceIdLong();
		this.peopleDao.updatePeopleRelationStatusByFromIdAndToId(PeopleRelationStatus.STRANGER, fromPeopleId, toPeopleId);
		
		context.redirectServletPath("/people/" + toPeopleId);
	}

}