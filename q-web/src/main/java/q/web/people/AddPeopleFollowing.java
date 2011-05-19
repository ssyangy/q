/**
 * 
 */
package q.web.people;

import q.dao.PeopleDao;
import q.domain.PeopleRelation;
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
public class AddPeopleFollowing extends Resource {
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

		PeopleRelation oldRelation = this.peopleDao.getPeopleRelationByFromIdToId(fromPeopleId, toPeopleId);
		if (oldRelation == null) {
			PeopleRelation newRelation = new PeopleRelation(fromPeopleId, toPeopleId, PeopleRelationStatus.FOLLOWING);
			this.peopleDao.addPeopleRelation(newRelation);
			this.peopleDao.incrPeopleFollowingNumberByPeopleId(fromPeopleId);
			this.peopleDao.incrPeopleFollowerNumberByPeopleId(toPeopleId);
		} else if (oldRelation.isStranger()) {
			int rowEffected = this.peopleDao.updatePeopleRelationStatusById(PeopleRelationStatus.FOLLOWING, PeopleRelationStatus.STRANGER, oldRelation.getId());
			if (rowEffected > 0) {
				this.peopleDao.incrPeopleFollowingNumberByPeopleId(fromPeopleId);
				this.peopleDao.incrPeopleFollowerNumberByPeopleId(toPeopleId);
			}
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
			throw new RequestParameterInvalidException("toPeople:不能关注自己");
		}
	}

}
