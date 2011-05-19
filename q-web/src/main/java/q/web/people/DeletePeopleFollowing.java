/**
 * 
 */
package q.web.people;

import q.dao.PeopleDao;
import q.domain.PeopleRelationStatus;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long fromPeopleId = context.getCookiePeopleId();
		long toPeopleId = context.getResourceIdLong();
		int rowEffected = this.peopleDao.updatePeopleRelationStatusByFromIdAndToId(PeopleRelationStatus.STRANGER, PeopleRelationStatus.FOLLOWING, fromPeopleId, toPeopleId);
		if (rowEffected > 0) {
			this.peopleDao.decrPeopleFollowingNumberByPeopleId(fromPeopleId);
			this.peopleDao.decrPeopleFollowerNumberByPeopleId(toPeopleId);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long fromPeopleId = context.getCookiePeopleId();
		long toPeopleId = context.getResourceIdLong();
		if (fromPeopleId == toPeopleId) {
			throw new RequestParameterInvalidException("toPeople:不能解除关注自己");
		}
	}

}
