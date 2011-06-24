/**
 * 
 */
package q.web.people;

import q.biz.NotifyService;
import q.dao.PeopleDao;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
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

	/**
	 * @param notifyService
	 *            the notifyService to set
	 */
	public void setNotifyService(NotifyService notifyService) {
		this.notifyService = notifyService;
	}

	private NotifyService notifyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long fromPeopleId = context.getCookiePeopleId();
		long toPeopleId = context.getResourceIdLong();

		PeopleRelation relation = this.peopleDao.getPeopleRelationByFromIdToId(fromPeopleId, toPeopleId);
		if (relation == null || relation.isStranger()) {
			if (relation == null) {
				PeopleRelation newRelation = new PeopleRelation(fromPeopleId, toPeopleId, PeopleRelationStatus.FOLLOWING);
				this.peopleDao.addPeopleRelation(newRelation);
				this.peopleDao.incrPeopleFollowingNumberByPeopleId(fromPeopleId);
				this.peopleDao.incrPeopleFollowerNumberByPeopleId(toPeopleId);
				relation = newRelation;
			} else if (relation.isStranger()) {
				int rowEffected = this.peopleDao.updatePeopleRelationStatusById(PeopleRelationStatus.FOLLOWING, PeopleRelationStatus.STRANGER, relation.getId());
				if (rowEffected > 0) {
					this.peopleDao.incrPeopleFollowingNumberByPeopleId(fromPeopleId);
					this.peopleDao.incrPeopleFollowerNumberByPeopleId(toPeopleId);
				}
			}
			// notify new message
			notifyService.notifyPeopleFollowing(relation);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long toPeopleId = context.getResourceIdLong();
		if (loginPeopleId == toPeopleId) {
			throw new RequestParameterInvalidException("toPeople:不能关注自己");
		}
	}

}
